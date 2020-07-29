package geometry.visible;

import biuoop.DrawSurface;
import geometry.invisible.Point;
import handlers.graphic.Sprite;
import handlers.levelParser.BackgroundFill;

import java.awt.Color;

/**
 * circle.
 */
public class Circle implements Sprite {
    private Point center;
    private int radius;
    private BackgroundFill color;

    /**
     * Constractor.
     *
     * @param c center
     * @param r radius
     */
    public Circle(Point c, int r) {
        center = c;
        radius = r;
        color = new BackgroundFill();
    }

    /**
     * Constractor.
     *
     * @param c   center
     * @param r   radius
     * @param col color
     */
    public Circle(Point c, int r, BackgroundFill col) {
        center = c;
        radius = r;
        color = col;
    }

    /**
     * Constractor.
     *
     * @param c   center
     * @param r   radius
     * @param col color
     */
    public Circle(Point c, int r, Color col) {
        center = c;
        radius = r;
        color = new BackgroundFill();
        color.addColor(col);
    }

    /**
     * Constractor.
     *
     * @param x   x center
     * @param y   y center
     * @param r   radius
     * @param col color
     */
    public Circle(int x, int y, int r, Color col) {
        this(new Point(x, y), r, col);
    }

    /**
     * add color.
     * @param c c
     * @param sym sym
     */
    public void addColor(Color c, String sym) {
        color.addColor(c, sym);
    }

    /**
     * draw object.
     *
     * @param surface draw surface
     */
    public void drawOn(DrawSurface surface) {
        Color fill = color.getColor("d");
        Color stroke = color.getColor("s");
        if (fill != null) {
            surface.setColor(fill);
            surface.fillCircle((int) Math.round(center.getX()), (int) Math.round(center.getY()), radius);
        }
        if (stroke != null) {
            surface.setColor(stroke);
            surface.drawCircle((int) Math.round(center.getX()), (int) Math.round(center.getY()), radius);
        }
    }

    /**
     * time.
     * @param dt dt
     */
    public void timePassed(double dt) {
    }

    /**
     * set  center.
     * @param c center
     */
    public void setCenter(Point c) {
        this.center = c;
    }

    /**
     * get center point.
     *
     * @return center point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * get x coord of point.
     *
     * @return x coord
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * get y coord of point.
     *
     * @return y coord
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * get radius of ball.
     *
     * @return radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * get color.
     *
     * @return color
     */
    public Color getColor() {
        return this.color.getColor("d");
    }
}
