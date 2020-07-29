package geometry.visible;

import biuoop.DrawSurface;
import geometry.invisible.Point;
import handlers.graphic.Sprite;

import java.awt.Color;

/**
 * drawLine.
 */
public class DrawableLine implements Sprite {
    private Line line;
    private Color color;

    /**
     * Constractor.
     *
     * @param start start point
     * @param end   end point
     * @param c     c
     */
    public DrawableLine(Point start, Point end, Color c) {
        line = new Line(start, end);
        color = c;
    }

    /**
     * Constractor by x,y.
     *
     * @param x1 point 1
     * @param y1 point 1
     * @param x2 point 2
     * @param y2 point 2
     * @param c  c
     */
    public DrawableLine(double x1, double y1, double x2, double y2, Color c) {
        this(new Point(x1, y1), new Point(x2, y2), c);
    }

    /**
     * draw object.
     *
     * @param surface draw surface
     */
    public void drawOn(DrawSurface surface) {
        if (color != null) {
            surface.setColor(color);
            surface.drawLine((int) Math.round(line.start().getX()), (int) Math.round(line.start().getY()),
                    (int) Math.round(line.end().getX()), (int) Math.round(line.end().getY()));
        }
    }

    /**
     * alart that time passed.
     * @param dt dt
     */
    public void timePassed(double dt) {
    }
}
