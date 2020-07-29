package graphics.bar;

import geometry.invisible.Point;

import java.awt.Color;

import biuoop.DrawSurface;
import handlers.graphic.Sprite;

/**
 * gameManger name.
 */
public class LevelName implements Sprite {
    private Point point;
    private String name;

    /**
     * Constructor.
     *
     * @param p point
     * @param n text
     */
    public LevelName(Point p, String n) {
        point = p;
        name = n;
    }

    /**
     * draw.
     *
     * @param d drawsurface.
     */
    public void drawOn(DrawSurface d) {

        String text = "Level Name: " + name;
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
