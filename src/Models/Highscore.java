package Models;

public class Highscore {
    private int _id;
    private String _name;
    private int _score;

    public Highscore(int id, String name, int score){
        this._id = id;
        this._name = name;
        this._score = score;
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

    public int get_score() {
        return _score;
    }

    public void setScore(int score) {
        this._score = score;
    }
}
