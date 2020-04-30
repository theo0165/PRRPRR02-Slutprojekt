import Models.Highscore;
import Models.Question;
import Models.Quiz;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataHelper {
    public static void main(String[] args) {
        getQuizzes();
    }

    private static String PATH = Paths.get("src/uploads/quizzes").toAbsolutePath().toString() + "/";

    // https://stackoverflow.com/a/5328933/5181428
    private static String generateId(){
        return  Long.toString((long) (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L));
    }

    private static boolean checkForId(String id){
        try {
            File file = new File(PATH + id + ".json");

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

        try{
            File folder = new File(PATH);
            File[] fileList = folder.listFiles();

            for(int i=0; i<fileList.length; i++){
                if(fileList[i].isFile() && fileList[i].getName() != "schema.json"){
                    String fileName = fileList[i].getName().substring(0, fileList[i].getName().length() - 5);
                    quizzes.add(getQuiz(fileName));
                }
            }
        }catch (Exception e){
            return null;
        }

        return quizzes;
    }

    public static Quiz getQuiz(String id){
        if(checkForId(id)){
            System.out.println("DEBUG: ID OK");
            //COMPARE TO SCHEMA
            try {
                FileReader file = new FileReader(PATH + id + ".json");
                JSONParser parser = new JSONParser();
                System.out.println("DEBUG: FILE INITIALIZED");

                try {
                    Object obj = parser.parse(file);
                    JSONObject json = (JSONObject) obj;
                    System.out.println("DEBUG: JSON PARSED");

                    ArrayList<Question> questions = new ArrayList<>();

                    for(int i=0; i < ((JSONArray)json.get("questions")).size(); i++){
                        JSONArray array = ((JSONArray)json.get("questions"));
                        JSONObject object = (JSONObject) array.get(i);
                        ArrayList<String> incorrect = new ArrayList<>();

                        for(int i1=0; i<((JSONArray)object.get("incorrect")).size(); i++){
                            incorrect.add(((JSONArray)object.get("incorrect")).get(i1).toString());
                        }

                        questions.add(new Question(
                                object.get("q").toString(),
                                object.get("correct").toString(),
                                incorrect
                        ));
                    }

                    //System.out.println(((JSONObject)((JSONArray)json.get("questions")).get(0)).get("q"));

                    Quiz quiz = new Quiz(
                            Integer.parseInt(json.get("id").toString()),
                            json.get("name").toString(),
                            Integer.parseInt(json.get("difficulty").toString()),
                            questions
                    );

                    return quiz;
                }catch (ParseException e){
                    System.out.println("DEBUG: COULD NOT PARSE");
                } catch (IOException e) {
                    System.out.println("DEBUG: IOException");

                }

            }catch (FileNotFoundException e){
                System.out.println("DEBUG: FILE NOT FOUND");
                System.out.println("DEBUG: " + PATH + id + ".json");
                return null;
            }
        }else{
            System.out.println("DEBUG: ID NOT OK");
            return null;
        }

        return null;
    }

    public static void importQuiz(String file){
        String id = generateId();
        boolean idGood = false;
        try {
            FileReader toImport = new FileReader(file);

            //Check file extension for .json
            if(file.lastIndexOf(".") != -1 && file.lastIndexOf(".") != 0 && file.substring(file.lastIndexOf(".") + 1) == "json"){
                // VALIDATE JSON
                if(true){
                    //JSON VALID
                    while(!idGood) {
                        File newFile = new File(PATH + id + ".json");
                        try {
                            if (!checkForId(id)) {
                                idGood = true;

                                if(newFile.createNewFile()){
                                    FileWriter writer = new FileWriter(PATH + id + ".json");
                                    writer.write(toImport.read());
                                    writer.close();
                                    toImport.close();
                                }
                            }else{
                                id = generateId();
                            }
                        } catch (Exception e) {
                            // SEND ERROR TO USER
                        }
                    }
                }
            }
        }catch (FileNotFoundException e){
            //SEND ERROR TO USER
        }
    }

    public static void saveHighscore(String name, int score, int id){ }

    public static ArrayList<Highscore> getHighscores(){
        return null;
    }
}
