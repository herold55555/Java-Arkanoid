package handlers.highScores;

import settings.GameStandarts;

import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * high.
 */
public class HighScoresTable implements Serializable {

    private List<ScoreInfo> scoreInfos;
    private int listSize;

    /**
     * con.
     *
     * @param size size
     */
    public HighScoresTable(int size) {
        scoreInfos = new LinkedList<>();
        listSize = size;
    }

    /**
     * add.
     *
     * @param score score
     */
    public void add(ScoreInfo score) {
        int place = getRank(score.getScore());
        if (place >= listSize) {
            return;
        }
        scoreInfos.add(place, score);
        deleteOver();
    }

    /**
     * delete.
     */
    private void deleteOver() {
        if (scoreInfos.size() > GameStandarts.SCORE_TABLE_SIZE) {
            scoreInfos.remove(GameStandarts.SCORE_TABLE_SIZE);
        }
    }

    /**
     * size.
     *
     * @return int
     */
    public int size() {
        return listSize;
    }

    /**
     * highscore.
     *
     * @return list
     */
    public List<ScoreInfo> getHighScores() {
        return scoreInfos;
    }

    /**
     * rank.
     *
     * @param score score
     * @return int
     */
    public int getRank(int score) {
        int i = 0;
        for (ScoreInfo s : scoreInfos) {
            if (s.getScore() < score) {
                return i;
            }
            i++;
        }
        return i;
    }

    /**
     * clear.
     */
    public void clear() {
        scoreInfos.clear();
    }

    /**
     * load.
     *
     * @param filename file
     * @throws IOException excption
     */
    public void load(File filename) throws IOException {
        HighScoresTable tmp;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tmp = (HighScoresTable) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            throw new IOException("IO Exception");
        } catch (ClassNotFoundException c) {
            throw new IOException("Class Not Found");
        }
        this.scoreInfos = tmp.getHighScores();
        this.listSize = tmp.size();
    }

    /**
     * load.
     *
     * @param filename file
     * @throws IOException excption
     */
    public void save(File filename) throws IOException {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            throw new IOException("IO Exception");
        }
    }

    /**
     * high.
     *
     * @param filename file
     * @return table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable tmp = new HighScoresTable(0);
        try {
            tmp.load(filename);
        } catch (IOException e) {
            return new HighScoresTable(GameStandarts.SCORE_TABLE_SIZE);
        }
        return tmp;
    }

    /**
     * main.
     * @param args args
     */
    public static void main(String[] args) {
        HighScoresTable a = new HighScoresTable(5);
        a.add(new ScoreInfo("Arad", 1000));
        a.add(new ScoreInfo("Lior", -1));
        a.add(new ScoreInfo("Or", 50));
        a.add(new ScoreInfo("as", 0));
        File file = new File("try.txt");
        try {
            a.save(file);
            HighScoresTable tmp = HighScoresTable.loadFromFile(file);
            System.out.println(tmp.listSize);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}