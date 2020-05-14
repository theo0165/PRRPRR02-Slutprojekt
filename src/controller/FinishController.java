package controller;

import Models.Quiz;
import helpers.DataHelper;
import helpers.LanguageHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class FinishController {
    double id;
    int score;
    Quiz quiz;

    @FXML
    Text scoreMessage;

    @FXML
    Text scoreValue;

    @FXML
    Button exitBtn;

    @FXML
    Button saveBtn;

    @FXML
    TextField nameInput;

    public FinishController(double _id, int _score){
        this.id = _id;
        this.score = _score;

        DecimalFormat formatter = new DecimalFormat("0.##");
        this.quiz = DataHelper.getQuiz(formatter.format(id).toString());
    }

    public void initialize(){
        scoreValue.setText(score + "/" + quiz.getQuestions().size());
    }

    public void handleExit(MouseEvent event){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
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

    public void handleSaveScore(MouseEvent event){
        DataHelper.saveHighscore(nameInput.getText(), score, id);

        Stage stage = (Stage) saveBtn.getScene().getWindow();
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
