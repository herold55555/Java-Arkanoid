package graphics.bar;

import biuoop.DrawSurface;
import geometry.invisible.Point;
import geometry.visible.DrawableRectangle;
import handlers.gameTrackers.Counter;
import handlers.graphic.Sprite;
import settings.GameStandarts;

import java.awt.Color;

/**
 * Bar.
 */
public class Bar implements Sprite {
    private DrawableRectangle rectangle;
    private LevelName levelName;
    private LivesIndicator livesIndicator;
    private ScoreIndicator scoreIndicator;

    /**
     * Constractor.
     *
     * @param levelN gameManger name
     * @param score  highScores
     * @param lives  lives
     */
    public Bar(String levelN, Counter score, Counter lives) {
        createRectangleBar();
        initialAll(levelN, score, lives);
    }

    /**
     * Create Rec.
     */
    private void createRectangleBar() {
        Point zero = new Point(0, 0);
        rectangle = new DrawableRectangle(zero, GameStandarts.BAR_SIZE, GameStandarts.HEIGHT,
                new Color(217, 217, 217));
    }

    /**
     * Initial.
     *
     * @param levelN gameManger name
     * @param score  highScores
     * @param lives  lives
     */
    private void initialAll(String levelN, Counter score, Counter lives) {
        Point lN = new Point(10, 18);
        Point lI = new Point(GameStandarts.HEIGHT - 60, 18);
        Point sI = new Point(GameStandarts.HEIGHT / 2 - 45, 18);
        levelName = new LevelName(lN, levelN);
        livesIndicator = new LivesIndicator(lI, lives);
        scoreIndicator = new ScoreIndicator(sI, score);
    }

    /**
     * draw.
     *
     * @param d drawsurface.
     */
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d);
        levelName.drawOn(d);
        livesIndicator.drawOn(d);
        scoreIndicator.drawOn(d);
    }

    /**
     * move one step, and check collisions.
     *
     * @param dt dt
     */
    public void timePassed(double dt) {

    }
}
