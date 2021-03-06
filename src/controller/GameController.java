package controller;

import Models.Question;
import Models.Quiz;

import Models.QuizList;
import helpers.DataHelper;
import helpers.LanguageHelper;
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

/***
 * Controller for quiz select screen
 */
public class GameController {
    @FXML
    Text backBtn;

    @FXML
    TableView quizList;

    public void initialize(){
        insertQuizList();
    }

    /***
     * Insert data to list of quizzes
     */
    public void insertQuizList(){
        ArrayList<Quiz> quizzes = DataHelper.getQuizzes(); // Get list of quizzes from DataHelper

        for(int i=0; i<quizzes.size(); i++){
            Quiz quiz = quizzes.get(i);

            double id = quiz.getId();
            String name = quiz.getName();
            int questions = quiz.getQuestions().size();
            int difficulty = quiz.getDifficulty();

            System.out.println(quiz.getQuestions().size());
            for(Question q : quiz.getQuestions()){
                System.out.println(q.getQuestion());
            }

            quizList.getItems().add(new QuizList(name, questions, difficulty, id)); // Separate class for quiz items in list
        }
    }

    /***
     * Go back to home screen when back button is pressed
     * @param mouseEvent
     * @throws IOException
     */
    public void handleBack(MouseEvent mouseEvent) throws IOException {
        //Go back to home screen
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"), LanguageHelper.getCurrentLanguage());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
