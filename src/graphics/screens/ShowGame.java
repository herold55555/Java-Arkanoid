package graphics.screens;

import graphics.levelData.LevelInformation;
import handlers.graphic.AnimationRunner;
import handlers.gameManger.GameFlow;
import handlers.gameManger.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Show game.
 */
public class ShowGame implements Task<Void> {

    private AnimationRunner runner;
    private List<LevelInformation> levels;

    /**
     * Instantiates a new Show game.
     *
     * @param runner the runner
     * @param l      the l
     */
    public ShowGame(AnimationRunner runner, List<LevelInformation> l) {
        this.runner = runner;
        levels = l;
    }

    /**
     * Instantiates a new Show game.
     *
     * @param runner the runner
     */
    public ShowGame(AnimationRunner runner) {
        this(runner, new LinkedList<>());
    }

    /**
     * run.
     * @return Void
     */
    public Void run() {
        GameFlow gameFlow = new GameFlow(runner);
        gameFlow.setLevelsToRun(cloneLevels());
        gameFlow.run();
        return null;
    }

    /**
     * cloneAll.
     * @return clones
     */
    private List<LevelInformation> cloneLevels() {
        List<LevelInformation> clonedLevels = new LinkedList<>();
        for (LevelInformation level : levels) {
            clonedLevels.add(level.clone());
        }
        return clonedLevels;
    }
}