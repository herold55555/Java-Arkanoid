package graphics.screens;

import handlers.graphic.SpriteCollection;
import handlers.graphic.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * CountdownAnimation.
 */
public class CountdownAnimation implements Animation {

    private Sleeper sleeper;
    private double numberOfSec;
    private int countFrom;
    private SpriteCollection shot;
    private boolean firstTime;

    /**
     * Constractor.
     *
     * @param numOfSeconds numberOfSec
     * @param countF       countF
     * @param gameScreen   gameScreen
     */
    public CountdownAnimation(double numOfSeconds, int countF, SpriteCollection gameScreen) {
        sleeper = new Sleeper();
        numberOfSec = numOfSeconds / countF;
        countFrom = countF;
        shot = gameScreen;
        firstTime = true;
    }

    /**
     * draw one frame.
     *
     * @param d  DrawSurface
     * @param dt dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.shot.drawAllOn(d);
        d.setColor(Color.white);
        if (firstTime) {
            d.drawText(d.getWidth() / 2 - 30, d.getHeight() / 2, countFrom + "...", 60);
            firstTime = false;
            countFrom--;
            return;
        }
        int n = (int) (numberOfSec * 1000);
        d.drawText(d.getWidth() / 2 - 30, d.getHeight() / 2, countFrom + "...", 60);
        countFrom--;
        sleeper.sleepFor(n);
    }

    /**
     * should stop.
     *
     * @return boolean
     */
    public boolean shouldStop() {
        if (countFrom == 0) {
            return true;
        }
        return false;
    }
}