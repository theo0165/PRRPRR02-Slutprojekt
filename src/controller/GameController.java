package controller;

import Models.Quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {
    @FXML
    Text backBtn;

    @FXML
    TableView quizList;

    public void initialize(){
        insertQuizList();
    }

    public void insertQuizList(){
        ArrayList<Quiz> quizzes;
    }

    public void handleBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
