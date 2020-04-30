import Models.Highscore;
import Models.Question;
import Models.Quiz;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class DataHelper {
    private static String PATH = "./uploads/quizzes/";

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
        return null;
    }

    public static Quiz getQuiz(String id){
        if(checkForId(id)){
            try {
                FileReader file = new FileReader(PATH + id + ".json");
                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(file);
                    JSONObject json = (JSONObject) obj;

                    ArrayList<Question> questions = new ArrayList<>();

                    Quiz quiz = new Quiz(
                            json.get("id"),
                            json.get("name"),
                            json.get("difficulty"),
                            questions
                    )
                }catch (ParseException e){

                } catch (IOException e) {

                }

            }catch (FileNotFoundException e){
                return null;
            }
        }else{
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
