import Models.Highscore;
import Models.Quiz;

import java.io.File;
import java.util.ArrayList;

public class DataHelper {
    private static String PATH = "./uploads/quizzes/";

    // https://stackoverflow.com/a/5328933/5181428
    private static String generateId(){
        return  Long.toString((long) (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L));
    }

    private static boolean checkForId(String id){
        File file = new File(PATH + id + ".json");

        if(file.exists() && file.isFile()){
            return true;
        }else {
            return false;
        }
    }

    public static ArrayList<Quiz> getQuizzes(){
        return null;
    }

    public static Quiz getQuiz(String id){
        return null;
    }

    public static void importQuiz(){
        String id = generateId();

        if(!checkForId(id)){

        }
    }

    public static void saveHighscore(String name, int score, int id){ }

    public static ArrayList<Highscore> getHighscores(){
        return null;
    }
}
