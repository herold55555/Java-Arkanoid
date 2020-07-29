package graphics.background;

import geometry.invisible.Point;
import geometry.visible.DrawablePolygon;
import settings.GameStandarts;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * generator.
 */
public class BackgroundGenerator {

    private Background background;
    private Random random;

    /**
     * constracot.
     */
    public BackgroundGenerator() {
        background = new Background();
        random = new Random();
    }

    /**
     * create.
     */
    public void createBackground() {
        double teta = random.nextDouble() * Math.PI;
        int xPointStart = (int) (-GameStandarts.HEIGHT / 2);
        int yPointStart = (int) (-GameStandarts.WIDTH / 2);
        int xPointEnd = (int) (GameStandarts.HEIGHT + (GameStandarts.HEIGHT / 2));
        int yPointEnd = (int) (GameStandarts.WIDTH + (GameStandarts.WIDTH / 2));
        int lenght = 80;
        for (int i = xPointStart; i < xPointEnd - lenght; i += lenght) {
            List<Point> points = new LinkedList<>();
            points.add(new Point(i, yPointStart));
            points.add(new Point(i + lenght, yPointStart));
            points.add(new Point(i + lenght, yPointEnd));
            points.add(new Point(i, yPointEnd));
            DrawablePolygon polygon = new DrawablePolygon(points, new Color(random.nextInt(16777215 + 1)));
            polygon.transforPoints(teta);
            background.addShape(polygon);
        }
    }

    /**
     * getback.
     * @return background
     */
    public Background getBackground() {
        return background;
    }

    /**
     * static to create.
     * @return backgound
     */
    public static Background createNewBackground() {
        BackgroundGenerator generator = new BackgroundGenerator();
        generator.createBackground();
        return generator.getBackground();
    }
}
