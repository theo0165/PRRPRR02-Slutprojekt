package controller;

import Models.Quiz;
import helpers.DataHelper;
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

public class FinishController {
    int id;
    int score;
    Quiz quiz;

    @FXML
    Text scoreMessage;

    @FXML
    Button exitBtn;

    @FXML
    Button saveBtn;

    @FXML
    TextField nameInput;

    public FinishController(int _id, int _score){
        this.id = _id;
        this.score = _score;
        this.quiz = DataHelper.getQuiz(String.valueOf(_id));
    }

    public void initialize(){
        scoreMessage.setText("You scored " + score + "/" + quiz.getQuestions().size());
    }

    public void handleExit(MouseEvent event){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
