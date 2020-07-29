package graphics.screens;

import biuoop.KeyboardSensor;
import handlers.graphic.AnimationRunner;
import handlers.graphic.KeyPressStoppableAnimation;
import handlers.highScores.HighScoresTable;
import handlers.gameManger.Task;
import settings.GameStandarts;

import java.io.File;

/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner the runner
     */
    public ShowHiScoresTask(AnimationRunner runner) {
        this.runner = runner;
        this.keyboardSensor = runner.getKeyboardSensor();
    }

    /**
     * run.
     * @return Void
     */
    public Void run() {
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(new File(GameStandarts.SCORE_BOARD));
        this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new HighScoresAnimation(highScoresTable)));
        return null;
    }
}
