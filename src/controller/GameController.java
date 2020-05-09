package controller;

import Models.Question;
import Models.Quiz;

import Models.QuizList;
import helpers.DataHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        ArrayList<Quiz> quizzes = DataHelper.getQuizzes();

        for(int i=0; i<quizzes.size(); i++){
            Quiz quiz = quizzes.get(i);

            int id = quiz.getId();
            String name = quiz.getName();
            int questions = quiz.getQuestions().size();
            int difficulty = quiz.getDifficulty();
            Button selectBtn = new Button("Select");

            System.out.println(quiz.getQuestions().size());
            for(Question q : quiz.getQuestions()){
                System.out.println(q.getQuestion());
            }

            quizList.getItems().add(new QuizList(name, questions, difficulty, id));
        }
    }

    public void handleBack(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
