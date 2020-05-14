package controller;

import Models.Highscore;
import Models.HighscoreList;
import helpers.DataHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreboardController {
    @FXML
    TableView scoreboardList;

    public void initialize(){
        insertScoreboardList();
    }

    private void insertScoreboardList(){
        ArrayList<Highscore> highscores = DataHelper.getHighscores();
        highscores.sort(Comparator.comparing(Highscore::getScore)); // Sort list after score, highest first

        for(int i=0;i<highscores.size(); i++){
            Highscore highscore = highscores.get(i);

            String name = highscore.getName();
            String quizName = DataHelper.getQuiz(String.valueOf(highscore.getId())).getName();
            int score = highscore.getScore();

            scoreboardList.getItems().add(new HighscoreList(name, quizName, score));
        }
    }
}
