package sample;

public class PairScore {

    private String[] name = new String[2];
    private double score;

    public PairScore()
    {
        score=-1;
    }

    public double getScore() {
        return score;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String name1, String name2) {
       name[0]= name1;
       name[1]= name2;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
