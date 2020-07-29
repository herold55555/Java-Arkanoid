package handlers.levelParser;

import geometry.GameObjects.Block;
import geometry.invisible.Velocity;
import graphics.levelData.LevelInformation;
import handlers.graphic.Sprite;

import java.util.List;
import java.util.LinkedList;

/**
 * The type Level.
 */
public class Level implements LevelInformation {

    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blockInGame;
    private List<Velocity> velocities;
    private String levelName;
    private Sprite background;
    private int numberOfBlocks;

    /**
     * Instantiates a new Level.
     */
    public Level() {
        numberOfBalls = -1;
        paddleSpeed = -1;
        paddleWidth = -1;
        blockInGame = null;
        velocities = null;
        levelName = null;
        background = null;
        numberOfBlocks = -1;
    }

    /**
     * Sets paddle speed.
     *
     * @param speed the paddle speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * Sets paddle width.
     *
     * @param width the paddle width
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * Sets block in game.
     *
     * @param blocks the block in game
     */
    public void setBlockInGame(List<Block> blocks) {
        this.blockInGame = blocks;
    }

    /**
     * Sets velocities.
     *
     * @param v the velocities
     */
    public void setVelocities(List<Velocity> v) {
        this.velocities = v;
        this.numberOfBalls = v.size();
    }

    /**
     * Sets level name.
     *
     * @param levelN the level name
     */
    public void setLevelName(String levelN) {
        this.levelName = levelN;
    }

    /**
     * Sets background.
     *
     * @param b the background
     */
    public void setBackground(Sprite b) {
        this.background = b;
    }

    /**
     * Sets number of blocks.
     *
     * @param numberOfBlock the number of blocks
     */
    public void setNumberOfBlocks(int numberOfBlock) {
        this.numberOfBlocks = numberOfBlock;
    }

    /**
     * numberOfBalls.
     *
     * @return numberOfBalls
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * initialBallVelocities.
     *
     * @return list velocity
     */
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    /**
     * paddlespeed.
     *
     * @return int
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * paddwidth.
     *
     * @return int
     */
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * gameManger name.
     *
     * @return string
     */
    public String levelName() {
        return levelName;
    }

    /**
     * get background.
     *
     * @return background
     */
    public Sprite getBackground() {
        return background;
    }

    /**
     * blocks.
     *
     * @return list block
     */
    public List<Block> blocks() {
        return blockInGame;
    }

    /**
     * numberOfBlocksToRemove.
     *
     * @return numberOfBlocksToRemove
     */
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }

    /**
     * Check vailed boolean.
     *
     * @return the boolean
     */
    public boolean checkVailed() {
        if (numberOfBalls == -1 || paddleSpeed == -1 || paddleWidth == -1 || blockInGame == null
                || velocities == null || levelName == null || background == null || numberOfBlocks == -1) {
            return false;
        }
        return true;
    }

    /**
     * clone.
     *
     * @return clone
     */
    public LevelInformation clone() {
        Level clonedLevel = new Level();
        clonedLevel.setPaddleWidth(paddleWidth);
        clonedLevel.setPaddleSpeed(paddleSpeed);
        clonedLevel.setNumberOfBlocks(numberOfBlocks);
        clonedLevel.setLevelName(levelName);
        clonedLevel.setBackground(background);
        List<Velocity> newVelocities = new LinkedList<>();
        for (Velocity v : velocities) {
            newVelocities.add(v.clone());
        }
        clonedLevel.setVelocities(newVelocities);
        List<Block> newBlocks = new LinkedList<>();
        for (Block b : blockInGame) {
            newBlocks.add(b.clone());
        }
        clonedLevel.setBlockInGame(newBlocks);
        return clonedLevel;
    }
}
