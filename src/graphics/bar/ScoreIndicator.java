package graphics.bar;

import handlers.gameTrackers.Counter;
import geometry.invisible.Point;

import java.awt.Color;

import biuoop.DrawSurface;
import handlers.graphic.Sprite;

/**
 * highScores indicator.
 */
public class ScoreIndicator implements Sprite {
    private Point point;
    private Counter scoreCounter;

    /**
     * constructor.
     *
     * @param p     point
     * @param score highScores
     */
    public ScoreIndicator(Point p, Counter score) {
        point = p;
        scoreCounter = score;
    }


    /**
     * draw.
     *
     * @param d drawsurface.
     */
    public void drawOn(DrawSurface d) {
        String text = "Score: " + scoreCounter.getValue();
        d.setColor(Color.BLACK);
        d.drawText((int) Math.round(point.getX()), (int) Math.round(point.getY()), text, 14);
    }

    /**
     * timePassed.
     *
     * @param dt dt
     */
    public void timePassed(double dt) {
    }
}
