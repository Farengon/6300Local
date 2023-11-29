package gtsi.sdp.windowsos.models;

public class Rank {

    private String stuName;
    private float score;
    private int ranking;

    public Rank(String stuName, float score, int ranking) {
        this.stuName = stuName;
        this.score = score;
        this.ranking = ranking;
    }

    public String getStuName() { return stuName; }

    public float getScore() { return score; }

    public int getRanking() { return ranking; }

    public void setScore(float score) { this.score = score; }
    public void setRanking(int rank) { this.ranking = rank; }
    public float calculateScore( int badHistory, int goodHistory ) {
        score = (float) (goodHistory - badHistory * 0.5);
        return score;
    }

}
