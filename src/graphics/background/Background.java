package graphics.background;

import handlers.graphic.Sprite;
import biuoop.DrawSurface;
import handlers.levelParser.BackgroundFill;
import settings.GameStandarts;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

/**
 * Background.
 */
public class Background implements Sprite {
    private List<Sprite> shapes;
    private BackgroundFill backgroundFill;

    /**
     * constractor.
     *
     * @param backgroundFill fill
     */
    public Background(BackgroundFill backgroundFill) {
        this.backgroundFill = backgroundFill;
        shapes = new LinkedList<>();
    }

    /**
     * constracor.
     */
    public Background() {
        this(new BackgroundFill());
    }

    /**
     * set fill.
     *
     * @param background fill
     */
    public void setBackgroundFill(BackgroundFill background) {
        this.backgroundFill = background;
    }

    /**
     * add shape.
     *
     * @param s shape
     */
    public void addShape(Sprite s) {
        shapes.add(s);
    }

    /**
     * draw.
     *
     * @param surface draw surface
     */
    public void drawOn(DrawSurface surface) {
        Image image = backgroundFill.getImage("d");
        Color color = backgroundFill.getColor("d");
        if (image != null) {
            surface.drawImage(0, 0, image);
        } else if (color != null) {
            surface.setColor(color);
            surface.fillRectangle(0, 0, GameStandarts.HEIGHT, GameStandarts.WIDTH);
        }
        for (Sprite shape : shapes) {
            shape.drawOn(surface);
        }
    }

    /**
     * time passed.
     *
     * @param dt dt
     */
    public void timePassed(double dt) {

    }
}
