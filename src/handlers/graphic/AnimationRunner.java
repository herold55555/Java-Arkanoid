package handlers.graphic;

import settings.GameStandarts;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * AnimationRunner.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private double framesPerSecond;

    /**
     * Constractor.
     */
    public AnimationRunner() {
        gui = new GUI("ARAD'S SPACE INVADER                          THE BEST IN THE WORLD!",
                GameStandarts.HEIGHT, GameStandarts.WIDTH);
        sleeper = new Sleeper();
        framesPerSecond = GameStandarts.FRAME_PER_SEC;
    }

    /**
     * run.
     *
     * @param animation animation.
     */
    public void run(Animation animation) {
        double millisecondsPerFrame = 1000 / framesPerSecond;
        double dt = 1 / framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, dt);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = Math.round(millisecondsPerFrame - usedTime);
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        DrawSurface d = gui.getDrawSurface();
        animation.doOneFrame(d, dt);
        gui.show(d);
    }

    /**
     * close gui.
     */
    public void close() {
        gui.close();
    }

    /**
     * get getKeyboardSensor.
     *
     * @return KeyboardSensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return gui.getKeyboardSensor();
    }

    /**
     * get gui.
     * @return gui
     */
    public GUI getGui() {
        return gui;
    }
}
