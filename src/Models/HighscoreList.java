package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HighscoreList {
    private SimpleStringProperty name;
    private SimpleStringProperty quizName;
    private SimpleIntegerProperty score;

    public HighscoreList(String _name, String _quizName, int _score){
        this.name = new SimpleStringProperty(_name);
        this.quizName = new SimpleStringProperty(_quizName);
        this.score = new SimpleIntegerProperty(_score);
    }

    public String getName() {
        return name.get();
    }

    public String getQuizName() {
        return quizName.get();
    }

    public int getScore() {
        return score.get();
    }
}
