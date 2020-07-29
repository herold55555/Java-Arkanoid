package graphics.screens;

import biuoop.DrawSurface;
import graphics.background.Background;
import graphics.background.BackgroundGenerator;
import handlers.highScores.HighScoresTable;
import handlers.highScores.ScoreInfo;
import handlers.graphic.Animation;

import java.awt.Color;

/**
 * high.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable highScoresTable;
    private Background background;

    /**
     * con.
     * @param scores scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        highScoresTable = scores;
        background = BackgroundGenerator.createNewBackground();
    }

    /**
     * doOneFrame.
     *
     * @param dt dt
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d, double dt) {
        background.drawOn(d);
        d.setColor(Color.BLACK);
        int index = 1;
        d.drawText(100, 50, "Rank", 20);
        d.drawText(250, 50, "Name", 20);
        d.drawText(400, 50, "Score", 20);
        for (ScoreInfo scoreInfo : highScoresTable.getHighScores()) {
            d.drawText(100, 60 + 30 * index, index + ".", 20);
            d.drawText(250, 60 + 30 * index, scoreInfo.getName(), 20);
            d.drawText(400, 60 + 30 * index, "" + scoreInfo.getScore(), 20);
            index++;
        }
    }

    /**
     * shouldStop.
     *
     * @return shouldStop
     */
    public boolean shouldStop() {
        return false;
    }
}