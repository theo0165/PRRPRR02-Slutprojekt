package Models;

import java.util.ArrayList;

public class Quiz {
    private double _id;
    private String _name;
    private int _difficulty; // 1 - 5
    private ArrayList<Question> _questions;

    public Quiz(double id, String name, int difficulty, ArrayList<Question> questions){
        this._id = id;
        this._name = name;
        this._difficulty = difficulty;
        this._questions = questions;
    }

    public double getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public int getDifficulty() {
        return _difficulty;
    }

    public ArrayList<Question> getQuestions() {
        return _questions;
    }
}
