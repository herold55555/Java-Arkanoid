package graphics.screens;

import graphics.background.Background;
import graphics.background.BackgroundGenerator;
import handlers.graphic.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * lose message.
 */
public class LoseMessage implements Animation {

    private int score;
    private Background background;

    /**
     * Constracor.
     *
     * @param s highScores
     */
    public LoseMessage(int s) {
        score = s;
        background = BackgroundGenerator.createNewBackground();
    }

    /**
     * draw one frame.
     *
     * @param dt dt
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d, double dt) {
        background.drawOn(d);
        d.setColor(Color.RED);
        d.drawText(194, d.getHeight() / 2 - 20, "Ohh To Bad... You Lose!", 40);
        d.drawText(244, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.WHITE);
        d.drawText(197, d.getHeight() / 2 - 20, "Ohh To Bad... You Lose!", 40);
        d.drawText(247, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.RED);
        d.drawText(200, d.getHeight() / 2 - 20, "Ohh To Bad... You Lose!", 40);
        d.drawText(250, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.WHITE);
        d.drawText(203, d.getHeight() / 2 - 20, "Ohh To Bad... You Lose!", 40);
        d.drawText(253, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.RED);
        d.drawText(202, d.getHeight() / 2 - 20, "Ohh To Bad... You Lose!", 40);
        d.drawText(252, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.drawText(100, 100, ":(", 40);
        d.drawText(300, 500, ":(", 40);
        d.drawText(700, 300, ":(", 40);
    }

    /**
     * should stop.
     *
     * @return boolean
     */
    public boolean shouldStop() {
        return false;
    }
}
