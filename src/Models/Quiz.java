package Models;

import java.util.ArrayList;

public class Quiz {
    private int _id;
    private String _name;
    private int _difficulty; // 1 - 5
    private ArrayList<Question> _questions;

    public Quiz(int id, String name, int difficulty, ArrayList<Question> questions){
        this._id = id;
        this._name = name;
        this._difficulty = difficulty;
        this._questions = questions;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public int getDifficulty() {
        return _difficulty;
    }

    public void setDifficulty(int difficulty) {
        this._difficulty = difficulty;
    }

    public ArrayList<Question> getQuestions() {
        return _questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this._questions = questions;
    }
}
