package graphics.screens;

import graphics.background.Background;
import graphics.background.BackgroundGenerator;
import handlers.graphic.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * win message.
 */
public class WinMessage implements Animation {

    private Background background;
    private int score;

    /**
     * constractor.
     *
     * @param s highScores
     */
    public WinMessage(int s) {
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
        d.setColor(Color.BLUE);
        d.drawText(238, d.getHeight() / 2 - 20, "Wow Great Job! You Win!", 40);
        d.drawText(248, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.WHITE);
        d.drawText(239, d.getHeight() / 2 - 20, "Wow Great Job! You Win!", 40);
        d.drawText(249, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.BLUE);
        d.drawText(240, d.getHeight() / 2 - 20, "Wow Great Job! You Win!", 40);
        d.drawText(250, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.white);
        d.drawText(241, d.getHeight() / 2 - 20, "Wow Great Job! You Win!", 40);
        d.drawText(251, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.BLUE);
        d.drawText(241, d.getHeight() / 2 - 20, "Wow Great Job! You Win!", 40);
        d.drawText(251, d.getHeight() / 2 + 20, "Your Score Is: " + score, 40);
        d.setColor(Color.white);
        d.drawText(100, 100, ":)", 40);
        d.drawText(50, 500, ":)", 40);
        d.drawText(100, 400, ":)", 40);
        d.drawText(500, 50, ":)", 40);
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
