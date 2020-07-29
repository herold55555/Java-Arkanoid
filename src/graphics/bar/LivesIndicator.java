package graphics.bar;

import handlers.gameTrackers.Counter;
import geometry.invisible.Point;

import java.awt.Color;

import biuoop.DrawSurface;
import handlers.graphic.Sprite;

/**
 * lives indicators.
 */
public class LivesIndicator implements Sprite {
    private Point point;
    private Counter lifeCounter;

    /**
     * Constructor.
     *
     * @param p    point
     * @param life lifes
     */
    public LivesIndicator(Point p, Counter life) {
        point = p;
        lifeCounter = life;
    }


    /**
     * draw.
     *
     * @param d drawsurface.
     */
    public void drawOn(DrawSurface d) {
        String text = "Lives: " + lifeCounter.getValue();
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
