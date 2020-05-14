package helpers;

import Models.Highscore;
import Models.Question;
import Models.Quiz;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataHelper {
    private static final String PATH = Paths.get("src").toAbsolutePath().toString() + "/";
    private static final String QUIZ_PATH = PATH + "uploads/quizzes/";
    private static final String HIGHSCORE_PATH = PATH + "data/";

    // https://stackoverflow.com/a/5328933/5181428
    private static String generateId(){
        return  Long.toString((long) (Math.floor(Math.random() * 9_000_000L) + 1_000_000L));
    }

    private static boolean checkForId(String id){
        try {
            File file = new File(QUIZ_PATH + id + ".json");

            if (file.exists() && file.isFile()) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            System.out.println("Folder /uploads/quizzes does not exist!");
            throw e;
        }
    }

    public static ArrayList<Quiz> getQuizzes(){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        System.out.println("Getting quizzes");

        try{
            File folder = new File(QUIZ_PATH);
            File[] fileList = folder.listFiles();
            System.out.println(fileList.toString());

            for(int i=0; i<fileList.length; i++){
                System.out.println("GET LOOP");
                if(fileList[i].isFile() && fileList[i].getName() != "schema.json"){
                    System.out.println("Added to list");
                    String fileName = fileList[i].getName().substring(0, fileList[i].getName().length() - 5);
                    System.out.println(fileName);
                    Quiz quiz = getQuiz(fileName);
                    quizzes.add(quiz);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println(quizzes);
        return quizzes;
    }

    public static Quiz getQuiz(String id){
        if(checkForId(id)){
            System.out.println("DEBUG: ID OK");
            //COMPARE TO SCHEMA
            try {
                FileReader file = new FileReader(QUIZ_PATH + id + ".json");
                JSONParser parser = new JSONParser();
                System.out.println("DEBUG: FILE INITIALIZED");

                try {
                    Object obj = parser.parse(file);
                    JSONObject json = (JSONObject) obj;
                    System.out.println("DEBUG: JSON PARSED");

                    ArrayList<Question> questions = new ArrayList<>();

                    System.out.println("DEBUG: " + ((JSONArray)json.get("questions")).size());
                    int size = ((JSONArray)json.get("questions")).size();
                    for(int i=0; i < size; i++){
                        System.out.println("LOOP");
                        JSONArray array = ((JSONArray)json.get("questions"));
                        JSONObject object = (JSONObject) array.get(i);
                        ArrayList<String> incorrect = new ArrayList<>();

                        for(int j=0; j<((JSONArray)object.get("incorrect")).size(); j++){
                            incorrect.add(((JSONArray)object.get("incorrect")).get(j).toString());
                        }

                        questions.add(new Question(
                                object.get("q").toString(),
                                object.get("correct").toString(),
                                incorrect
                        ));
                    }

                    //System.out.println(((JSONObject)((JSONArray)json.get("questions")).get(0)).get("q"));

                    System.out.println("RETURNING");
                    return new Quiz(
                            Double.parseDouble(json.get("id").toString()),
                            json.get("name").toString(),
                            Integer.parseInt(json.get("difficulty").toString()),
                            questions
                    );
                }catch (ParseException e){
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println(e);

                }

            }catch (FileNotFoundException e){
                System.out.println("DEBUG: FILE NOT FOUND");
                System.out.println("DEBUG: " + QUIZ_PATH + id + ".json");
            }
        }else{
            System.out.println("DEBUG: ID NOT OK");
        }

        return null;
    }

    public static void importQuiz(String file){
        String id = generateId();
        boolean idGood = false;
        try {
            FileReader toImport = new FileReader(file);

            System.out.println("File opened");

            //Check file extension for .json
            if(file.lastIndexOf(".") != -1 && file.lastIndexOf(".") != 0 && file.substring(file.lastIndexOf(".") + 1).equals("json")){
                // VALIDATE JSON
                if(true){
                    //JSON VALID
                    System.out.println("JSON valid");
                    while(!idGood) {
                        File newFile = new File(QUIZ_PATH + id + ".json");
                        try {
                            if (!checkForId(id)) {
                                System.out.println("ID GOOD");
                                idGood = true;

                                if(newFile.createNewFile()){
                                    FileWriter writer = new FileWriter(QUIZ_PATH + id + ".json");
                                    JSONParser parser = new JSONParser();

                                    Object object = parser.parse(toImport);
                                    JSONObject json = (JSONObject) object;
                                    json.put("id", id);
                                    System.out.println(json.toString());

                                    writer.write(json.toString());
                                    writer.flush();
                                    writer.close();
                                    toImport.close();
                                    System.out.println("File written");
                                }
                            }else{
                                id = generateId();
                            }
                        } catch (Exception e) {
                            // SEND ERROR TO USER
                            System.out.println("Error");
                        }
                    }
                }
            }else{
                System.out.println("Invalid file");
            }
        }catch (FileNotFoundException e){
            //SEND ERROR TO USER
            System.out.println("Error 2");
        }
    }

    @SuppressWarnings("unchecked")
    public static void saveHighscore(String name, int score, double id){
        JSONParser parser = new JSONParser();

        try{
            File highscoreFile = new File(HIGHSCORE_PATH + "highscore.json");
            if(!highscoreFile.exists()){
                System.out.println("DEBUG: FILE DOES NOT EXISTS");
                highscoreFile.createNewFile();
            }

            FileReader reader = new FileReader(highscoreFile);
            Object content = parser.parse(reader);
            JSONObject jsonContent = (JSONObject) content;
            JSONArray highscoreArray = (JSONArray) jsonContent.get("highscores");

            JSONObject newData = new JSONObject();
            DecimalFormat formatter = new DecimalFormat("0.##");
            newData.put("name", name);
            newData.put("score", Integer.valueOf(formatter.format(score)));
            newData.put("id", id);

            highscoreArray.add(newData);
            System.out.println(jsonContent);

            reader.close();

            FileWriter writer = new FileWriter(HIGHSCORE_PATH + "highscore.json");
            writer.write(jsonContent.toString());
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println("LINE 216");
            System.out.println(e);
        }
    }

    public static ArrayList<Highscore> getHighscores(){
        ArrayList<Highscore> highscoreList = new ArrayList<>();

        try{
            JSONParser parser = new JSONParser();
            File highscoreFile = new File(HIGHSCORE_PATH + "highscore.json");
            if(!highscoreFile.exists()){
                System.out.println("DEBUG: FILE DOES NOT EXISTS");
                highscoreFile.createNewFile();
            }

            FileReader reader = new FileReader(HIGHSCORE_PATH + "highscore.json");
            Object content = parser.parse(reader);
            JSONObject jsonContent = (JSONObject) content;
            JSONArray highscores = (JSONArray) jsonContent.get("highscores");

            for(int i=0; i<highscores.size(); i++){
                JSONObject highscore = (JSONObject) highscores.get(i);
                highscoreList.add(new Highscore(
                        Integer.parseInt(highscore.get("id").toString()),
                        highscore.get("name").toString(),
                        Integer.parseInt(highscore.get("score").toString())
                ));
            }
        }catch(Exception e){
            System.out.println(e);
        }

        return highscoreList;
    }

    public static void changeLang(String lang){
        System.out.println(lang);
        try{
           File file = new File(HIGHSCORE_PATH + "language.txt");

           if(!file.exists()){
               file.createNewFile();
           }

           FileWriter writer = new FileWriter(HIGHSCORE_PATH + "language.txt");
           writer.write(lang);
           writer.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void clearHighscore(){
        try{
            File file = new File(HIGHSCORE_PATH + "highscore.json");

            if(file.exists() && file.isFile()){
                file.delete();
            }

            file.createNewFile();

            FileWriter writer = new FileWriter(HIGHSCORE_PATH + "highscore.json");
            writer.write("{\"highscores\":[]");
            writer.close();
        }catch (Exception e){

        }
    }

    public static String getCurrentLang() {
        try{
            File file = new File(HIGHSCORE_PATH + "language.txt");

            if(!file.exists() || !file.isFile()){
                file.createNewFile();

                FileWriter writer = new FileWriter(HIGHSCORE_PATH + "language.txt");
                writer.write("en");
                writer.close();

                return "en";
            }

            List<String> lines = Files.readAllLines(Paths.get(HIGHSCORE_PATH + "language.txt"), StandardCharsets.UTF_8);

            return lines.get(0);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
