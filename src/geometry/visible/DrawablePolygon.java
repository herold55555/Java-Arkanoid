package geometry.visible;

import biuoop.DrawSurface;
import geometry.invisible.Point;
import handlers.graphic.Sprite;
import settings.GameStandarts;

import java.awt.Color;
import java.awt.Polygon;
import java.util.List;

/**
 * draw polygon.
 */
public class DrawablePolygon implements Sprite {

//    1 0 -a          1 0 a
//A   0 1 -b  (A) -1  0 1 b
//    0 0  1          0 0 1

//    cos(a)  sin(a) 0
//B   -sin(a) cos(a) 0
//    0       0      1

    private int[] xPoints;
    private int[] yPoints;
    private Color color;

    /**
     * drawPoly.
     *
     * @param points points
     * @param c      c
     */
    public DrawablePolygon(List<Point> points, Color c) {
        xPoints = new int[points.size()];
        yPoints = new int[points.size()];
        int i = 0;
        for (Point point : points) {
            xPoints[i] = (int) Math.round(point.getX());
            yPoints[i] = (int) Math.round(point.getY());
            i++;
        }
        color = c;
    }

    /**
     * move poinrs.
     *
     * @param teta angle.
     */
    public void transforPoints(double teta) {
        for (int i = 0; i < xPoints.length; i++) {
            transforPoint(teta, i);
        }
    }

    /**
     * transfer point.
     *
     * @param teta  angle
     * @param index i
     */
    private void transforPoint(double teta, int index) {
        int a = (int) (GameStandarts.HEIGHT / 2);
        int b = (int) (GameStandarts.WIDTH / 2);
        int x = xPoints[index];
        int y = yPoints[index];
        double sin = Math.sin(teta);
        double cos = Math.cos(teta);
        xPoints[index] = (int) Math.round(x * cos + y * sin - a * cos - b * sin + a);
        yPoints[index] = (int) Math.round(-x * sin + y * cos + a * sin - b * cos + b);
    }

    /**
     * draw object.
     *
     * @param surface draw surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillPolygon(new Polygon(xPoints, yPoints, xPoints.length));
    }

    /**
     * alart that time passed.
     *
     * @param dt dt
     */
    @Override
    public void timePassed(double dt) {

    }
}
