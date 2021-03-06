package controller;

import Models.Highscore;
import Models.HighscoreList;
import helpers.DataHelper;
import helpers.LanguageHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Controller for scoreboard screen
 */
public class ScoreboardController {
    @FXML
    TableView scoreboardList;

    @FXML
    Text backBtn;

    public void initialize(){
        insertScoreboardList();
    }

    /**
     * Insert data into scoreboard list (TableView)
     */
    private void insertScoreboardList(){
        ArrayList<Highscore> highscores = DataHelper.getHighscores();
        highscores.sort(Comparator.comparing(Highscore::getScore).reversed()); // Sort list after score, highest first

        for(int i=0;i<highscores.size(); i++){
            Highscore highscore = highscores.get(i);

            String name = highscore.getName();
            String quizName = DataHelper.getQuiz(String.valueOf(highscore.getId())).getName(); // Get quiz from data helper, then get the name of that quiz
            int score = highscore.getScore();

            scoreboardList.getItems().add(new HighscoreList(name, quizName, score)); // Add data with the HighscoreList model
        }
    }

    /**
     * Go back to home screen
     * @param event
     */
    public void handleBack(MouseEvent event) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"), LanguageHelper.getCurrentLanguage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
