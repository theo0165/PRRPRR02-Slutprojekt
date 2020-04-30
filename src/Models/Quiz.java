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
