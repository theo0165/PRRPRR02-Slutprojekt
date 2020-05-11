package controller;

import Models.Question;
import Models.Quiz;
import helpers.DataHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class QuizController {
    int id;
    Quiz quiz;

    int index = 0;
    int correctIndex = 0;

    int score = 0;

    @FXML
    Text question;

    @FXML
    Button answerOne;

    @FXML
    Button answerTwo;

    @FXML
    Button answerThree;

    @FXML
    Button answerFour;

    @FXML
    Text counter;

    public QuizController(int _id){
        this.id = _id;
    }

    public void initialize(){
        quiz = DataHelper.getQuiz(String.valueOf(id));
        nextQuestion();
    }

    private void nextQuestion(){
        if(quiz.getQuestions().size() <= index){
            finishQuiz();
        }else{
            counter.setText((index+1) + "/" + quiz.getQuestions().size());
            Question questionList = quiz.getQuestions().get(index);
            ArrayList<String> randomized = new ArrayList<>(questionList.getIncorrect());
            randomized.add(questionList.getCorrect());

            Collections.shuffle(randomized);

            question.setText(questionList.getQuestion());

            for(int i=0; i < randomized.size(); i++){
                if(randomized.get(i) == questionList.getCorrect()){ //Check for correct index;
                    correctIndex = i;
                }

                switch (i){
                    case 0: answerOne.setText(randomized.get(i)); break;
                    case 1: answerTwo.setText(randomized.get(i)); break;
                    case 2: answerThree.setText(randomized.get(i)); break;
                    case 3: answerFour.setText(randomized.get(i)); break;
                }
            }
        }
    }

    private void finishQuiz(){
        Stage stage = (Stage) question.getScene().getWindow();
        FinishController finishController = new FinishController(id, score);
        FXMLLoader loader = null;
        loader = new FXMLLoader(getClass().getResource("../layout/FinishLayout.fxml"));

        loader.setController(finishController);

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }

    public void handleAnswerOne(MouseEvent event){
        if(correctIndex == 0){
            System.out.println("Correct");
            score++;
        }else {
            System.out.println("Incorrect");
        }

        index++;
        nextQuestion();
    }

    public void handleAnswerTwo(MouseEvent event){
        if(correctIndex == 1){
            System.out.println("Correct");
            score++;
        }else {
            System.out.println("Incorrect");
        }

        index++;
        nextQuestion();
    }

    public void handleAnswerThree(MouseEvent event){
        if(correctIndex == 2){
            System.out.println("Correct");
            score++;
        }else {
            System.out.println("Incorrect");
        }

        index++;
        nextQuestion();
    }

    public void handleAnswerFour(MouseEvent event){
        if(correctIndex == 3){
            System.out.println("Correct");
            score++;
        }else {
            System.out.println("Incorrect");
        }

        index++;
        nextQuestion();
    }
}
