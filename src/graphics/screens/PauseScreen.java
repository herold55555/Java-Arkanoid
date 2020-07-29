package graphics.screens;

import graphics.background.Background;
import graphics.background.BackgroundGenerator;
import handlers.graphic.Animation;
import biuoop.DrawSurface;
import settings.GameStandarts;

import java.awt.Color;

/**
 * pause screen.
 */
public class PauseScreen implements Animation {

    private Background background;

    /**
     * constracor.
     */
    public PauseScreen() {
        background = BackgroundGenerator.createNewBackground();
    }

    /**
     * draw one frame.
     *
     * @param dt dt
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d, double dt) {
        background.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(GameStandarts.HEIGHT / 2 - 40, GameStandarts.WIDTH / 2, "paused", 32);
    }

    /**
     * should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return false;
    }
}