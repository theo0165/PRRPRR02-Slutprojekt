import Models.Highscore;
import Models.Quiz;

import java.io.File;
import java.util.ArrayList;

public class DataHelper {
    private String PATH = "./uploads/quizzes/";

    // https://stackoverflow.com/a/5328933/5181428
    private String generateId(){
        return  Long.toString((long) (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L));
    }

    private boolean checkForId(String id){
        File file = new File(PATH + id + ".json");

        if(file.exists() && file.isFile()){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Quiz> getQuizzes(){
        return null;
    }

    public Quiz getQuiz(String id){
        return null;
    }

    public void importQuiz(){
        String id = generateId();

        if(!checkForId(id)){

        }
    }

    public void saveHighscore(String name, int score, int id){ }

    public ArrayList<Highscore> getHighscores(){
        return null;
    }
}
