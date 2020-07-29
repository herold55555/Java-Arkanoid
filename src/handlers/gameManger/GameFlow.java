package handlers.gameManger;

import biuoop.DialogManager;
import handlers.graphic.KeyPressStoppableAnimation;
import graphics.screens.WinMessage;
import graphics.screens.LoseMessage;
import graphics.screens.ShowHiScoresTask;
import handlers.highScores.HighScoresTable;
import handlers.highScores.ScoreInfo;
import handlers.gameTrackers.Counter;
import settings.GameStandarts;
import handlers.graphic.AnimationRunner;
import graphics.levelData.LevelDirectHit;
import graphics.levelData.LevelFinalFour;
import graphics.levelData.LevelGreen3;
import graphics.levelData.LevelWideEasy;
import graphics.levelData.LevelInformation;
import biuoop.KeyboardSensor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.File;

/**
 * game flow.
 */
public class GameFlow implements Task<Void> {

    private List<LevelInformation> levelsToRun;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter scoreCounter;
    private Counter livesCounter;

    /**
     * Constractor.
     *
     * @param runner runner
     */
    public GameFlow(AnimationRunner runner) {
        levelsToRun = new LinkedList<>();
        animationRunner = runner;
        keyboardSensor = runner.getKeyboardSensor();
        scoreCounter = new Counter();
        livesCounter = new Counter(GameStandarts.LIVES);
    }

    /**
     * set levels.
     *
     * @param levels levels
     */
    public void setLevelsToRun(List<LevelInformation> levels) {
        this.levelsToRun = levels;
    }

    /**
     * levelsToRun.
     *
     * @param levels graphic.levelData
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, animationRunner, keyboardSensor, scoreCounter, livesCounter);
            level.initialize();
            while (livesCounter.getValue() != 0) {
                level.playOneTurn();
                if (level.isLevelClear()) {
                    break;
                }
                livesCounter.decrease(1);
            }
            if (livesCounter.getValue() == 0) {
                break;
            }
        }

        if (livesCounter.getValue() == 0) {
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                    "space", new LoseMessage(scoreCounter.getValue())));
        } else {
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                    "space", new WinMessage(scoreCounter.getValue())));
        }
        checkScore(scoreCounter.getValue());
        new ShowHiScoresTask(animationRunner).run();
    }

    /**
     * run.
     *
     * @return Void
     */
    public Void run() {
        if (levelsToRun.size() == 0) {
            levelsToRun.add(new LevelDirectHit());
            levelsToRun.add(new LevelWideEasy());
            levelsToRun.add(new LevelGreen3());
            levelsToRun.add(new LevelFinalFour());
            runLevels(levelsToRun);
        } else {
            runLevels(levelsToRun);
        }
        return null;
    }

    /**
     * check score.
     *
     * @param score score
     */
    private void checkScore(int score) {
        File file = new File(GameStandarts.SCORE_BOARD);
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(file);
        int rank = highScoresTable.getRank(score);
        if (rank < GameStandarts.SCORE_TABLE_SIZE) {
            DialogManager dialog = animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            highScoresTable.add(new ScoreInfo(name, score));
            try {
                highScoresTable.save(file);
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }
}
