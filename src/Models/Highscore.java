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

    public String getName() {
        return _name;
    }

    public int getScore() {
        return _score;
    }
}
