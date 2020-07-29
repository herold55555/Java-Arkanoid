package graphics.levelData;

import geometry.GameObjects.Block;
import geometry.invisible.Velocity;
import handlers.graphic.Sprite;

import java.util.List;

/**
 * LevelInformation.
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     * @return numberOfBalls
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     * @return list velocity
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddlespeed.
     * @return int
     */
    int paddleSpeed();

    /**
     * paddwidth.
     * @return int
     */
    int paddleWidth();

    /**
     * gameManger name.
     * @return string
     */
    String levelName();

    /**
     * get background.
     * @return background
     */
    Sprite getBackground();

    /**
     * blocks.
     * @return list block
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove.
     * @return numberOfBlocksToRemove
     */
    int numberOfBlocksToRemove();

    /**
     * clone.
     * @return clone
     */
    LevelInformation clone();
}