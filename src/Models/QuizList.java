package Models;

import controller.QuizController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

//https://medium.com/@keeptoo/adding-data-to-javafx-tableview-stepwise-df582acbae4f
public class QuizList {
    private SimpleStringProperty name;
    private SimpleIntegerProperty questions;
    private SimpleIntegerProperty difficulty;
    private SimpleDoubleProperty id;
    private Button selectBtn;

    public QuizList(String _name, Integer _questions, Integer _difficulty, Double _id){
        this.name = new SimpleStringProperty(_name);
        this.questions = new SimpleIntegerProperty(_questions);
        this.difficulty = new SimpleIntegerProperty(_difficulty);
        this.id = new SimpleDoubleProperty(_id);
        this.selectBtn = new Button("Select");

        this.selectBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            QuizController quizController = new QuizController(this.id.get());
            FXMLLoader loader = null;
            loader = new FXMLLoader(getClass().getResource("../layout/QuizLayout.fxml"));

            loader.setController(quizController);

            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.show();
        });

        this.selectBtn.setId("selectBtn");
    }

    public String getName() {
        return name.get();
    }

    public int getQuestions() {
        return questions.get();
    }

    public int getDifficulty() {
        return difficulty.get();
    }

    public double getId() {
        return id.get();
    }

    public Button getSelectBtn() {
        return selectBtn;
    }
}
