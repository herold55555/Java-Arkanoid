package graphics.screens;

import handlers.graphic.AnimationRunner;
import handlers.gameManger.Task;

/**
 * EXIT.
 */
public class Exit implements Task<Void> {

    private AnimationRunner runner;

    /**
     * con.
     * @param runner runner.
     */
    public Exit(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * run.
     * @return Void.
     */
    public Void run() {
        runner.close();
        System.exit(0);
        return null;
    }
}
