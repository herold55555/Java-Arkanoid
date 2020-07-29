package geometry.visible;

import biuoop.DrawSurface;
import geometry.invisible.Point;
import handlers.graphic.Sprite;
import handlers.levelParser.BackgroundFill;
import settings.Logger;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

/**
 * drawrectangle.
 */
public class DrawableRectangle implements Sprite {
    private Rectangle rectangle;
    private BackgroundFill colors;

    /**
     * Constactor.
     *
     * @param upperLeft upper left point
     * @param width     WIDTH of rec
     * @param height    HEIGHT of rec
     */
    public DrawableRectangle(Point upperLeft, double width, double height) {
        rectangle = new Rectangle(upperLeft, width, height);
        colors = new BackgroundFill();
    }

    /**
     * Constactor.
     *
     * @param upperLeft upper left point
     * @param width     WIDTH of rec
     * @param height    HEIGHT of rec
     * @param c         colors of rec
     */
    public DrawableRectangle(Point upperLeft, double width, double height, Color c) {
        rectangle = new Rectangle(upperLeft, width, height);
        colors = new BackgroundFill();
        colors.addColor(c);
    }

    /**
     * Constactor.
     *
     * @param upperLeft upper left point
     * @param width     WIDTH of rec
     * @param height    HEIGHT of rec
     * @param c         colors of rec
     */
    public DrawableRectangle(Point upperLeft, double width, double height, BackgroundFill c) {
        rectangle = new Rectangle(upperLeft, width, height);
        colors = c;
    }

    /**
     * draw object.
     *
     * @param surface draw surface
     */
    public void drawOn(DrawSurface surface) {
        Color fill = colors.getColor("d");
        Image image = colors.getImage("d");
        Color stroke = colors.getColor("s");
        if (fill != null) {
            surface.setColor(fill);
            surface.fillRectangle((int) Math.round(rectangle.getUpperLeft().getX()),
                    (int) Math.round(rectangle.getUpperLeft().getY()), (int) Math.round(rectangle.getHeight()),
                    (int) Math.round(rectangle.getWidth()));
        } else if (image != null) {
            surface.drawImage((int) Math.round(rectangle.getUpperLeft().getX()),
                    (int) Math.round(rectangle.getUpperLeft().getY()), image);
        }
        if (stroke != null) {
            surface.setColor(stroke);
            surface.drawRectangle((int) Math.round(rectangle.getUpperLeft().getX()),
                    (int) Math.round(rectangle.getUpperLeft().getY()), (int) Math.round(rectangle.getHeight()),
                    (int) Math.round(rectangle.getWidth()));
        }
    }

    /**
     * draw object.
     *
     * @param hit hit
     * @param surface draw surface
     */
    public void drawOn(DrawSurface surface, String hit) {
        Color fill = colors.getColor(hit);
        Image image = colors.getImage(hit);
        Color stroke = colors.getColor("s");
        if (fill != null) {
            surface.setColor(fill);
            surface.fillRectangle((int) Math.round(rectangle.getUpperLeft().getX()),
                    (int) Math.round(rectangle.getUpperLeft().getY()), (int) Math.round(rectangle.getHeight()),
                    (int) Math.round(rectangle.getWidth()));
        } else if (image != null) {
            surface.drawImage((int) Math.round(rectangle.getUpperLeft().getX()),
                    (int) Math.round(rectangle.getUpperLeft().getY()), image);
        }
        if (stroke != null) {
            surface.setColor(stroke);
            surface.drawRectangle((int) Math.round(rectangle.getUpperLeft().getX()),
                    (int) Math.round(rectangle.getUpperLeft().getY()), (int) Math.round(rectangle.getHeight()),
                    (int) Math.round(rectangle.getWidth()));
        }
    }

    /**
     * time.
     * @param dt dt
     */
    public void timePassed(double dt) {
    }

    /**
     * set log.
     * @param l l
     */
    public void setLog(Logger l) {
        rectangle.setLog(l);
    }

    /**
     * get witdh.
     * @return width
     */
    public double getWidth() {
        return rectangle.getWidth();
    }

    /**
     * heigth.
     * @return heigth
     */
    public double getHeight() {
        return rectangle.getHeight();
    }

    /**
     * background.
     * @return background
     */
    public BackgroundFill getBackgroundFill() {
        return colors;
    }

    /**
     * return rectangle.
     *
     * @return this rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * get all rectangles line (with offsets).
     *
     * @return all rec lines
     */
    public List<Line> getRectangleLines() {
        return rectangle.getRectangleLines();
    }

    /**
     * get corners.
     * @return corners
     */
    public List<Point> getCornerPoints() {
        return rectangle.getCornerPoints();
    }

    /**
     * get upper left.
     * @return upper left
     */
    public Point getUpperLeft() {
        return rectangle.getUpperLeft();
    }

    /**
     * set upper left.
     * @param p ul
     */
    public void setUpperLeft(Point p) {
        rectangle.setUpperLeft(p);
    }
}
