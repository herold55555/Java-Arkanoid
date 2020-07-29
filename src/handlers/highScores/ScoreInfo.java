package handlers.highScores;

import java.io.Serializable;

/**
 * The type Score info.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param n the n
     * @param s the s
     */
    public ScoreInfo(String n, int s) {
        name = n;
        score = s;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }
}