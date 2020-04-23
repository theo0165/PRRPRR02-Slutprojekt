package Models;

import java.util.ArrayList;

public class Question {
    private String _question; // The question to be shown
    private String _correct; // The correct anwser
    private ArrayList<String> _incorrect; // 3 incorrect anwsers

    public Question(String question, String correct, ArrayList<String> incorrect){
        this._question = question;
        this._correct = correct;
        this._incorrect = incorrect;
    }

    public String getQuestion() {
        return _question;
    }

    public void setQuestion(String question) {
        this._question = question;
    }

    public String getCorrect() {
        return _correct;
    }

    public void setCorrect(String correct) {
        this._correct = correct;
    }

    public ArrayList<String> getIncorrect() {
        return _incorrect;
    }

    public void setIncorrect(ArrayList<String> incorrect) {
        this._incorrect = incorrect;
    }
}
