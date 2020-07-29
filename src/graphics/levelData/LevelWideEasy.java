package graphics.levelData;

import geometry.GameObjects.BlockType;
import geometry.visible.DrawableLine;
import geometry.visible.DrawableRectangle;
import geometry.visible.Circle;
import handlers.levelParser.Level;
import settings.GameStandarts;
import geometry.invisible.Point;
import geometry.invisible.Velocity;
import geometry.GameObjects.Block;
import graphics.background.Background;
import handlers.graphic.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * LevelWideEasy.
 */
public class LevelWideEasy implements LevelInformation {

    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blockInGame;
    private String levelName;
    private int numberOfBlocks;

    /**
     * constructor.
     */
    public LevelWideEasy() {
        numberOfBalls = 10;
        paddleSpeed = 1;
        numberOfBlocks = 0;
        paddleWidth = GameStandarts.HEIGHT - 150;
        levelName = "Wide Easy";
        blockInGame = initialBlocks();
    }

    /**
     * numofballs.
     *
     * @return int
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * initialBallVelocity.
     *
     * @return list velocity
     */
    public List<Velocity> initialBallVelocities() {
        Random random = new Random();
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            int angle = random.nextInt(121) + 120;
            velocities.add(Velocity.fromAngleAndSpeed(angle, GameStandarts.BALL_SPEED));
        }
        return velocities;
    }

    /**
     * paddleSpeed.
     *
     * @return paddleSpeed
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * paddleWidth.
     *
     * @return paddleWidth
     */
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * levelName.
     *
     * @return levelName
     */
    public String levelName() {
        return levelName;
    }

    /**
     * getBackground.
     *
     * @return getBackground
     */
    public Sprite getBackground() {
        Background b = new Background();
        DrawableRectangle rectangle = new DrawableRectangle(new Point(0, 0), GameStandarts.WIDTH,
                GameStandarts.HEIGHT, new Color(255, 0, 102));
        Color newYellow = new Color(255, 235, 153);
        b.addShape(rectangle);
        for (int i = GameStandarts.WALL_SIZE; i < GameStandarts.HEIGHT - GameStandarts.WALL_SIZE; i += 10) {
            DrawableLine l = new DrawableLine(110, 110, i, 200, newYellow);
            b.addShape(l);
        }
        Circle c1 = new Circle(110, 110, 85, newYellow);
        Circle c2 = new Circle(110, 110, 75, Color.ORANGE);
        Circle c3 = new Circle(110, 110, 50, Color.YELLOW);
        b.addShape(c1);
        b.addShape(c2);
        b.addShape(c3);
        return b;
    }

    /**
     * blocks.
     *
     * @return blocks
     */
    public List<Block> blocks() {
        return blockInGame;
    }

    /**
     * blocks.
     *
     * @return list blocks
     */
    public List<Block> initialBlocks() {
        List<Block> blocks = new LinkedList<>();
        blocks.addAll(walls());
        blocks.addAll(block());
        return blocks;
    }

    /**
     * walls.
     *
     * @return list block
     */
    private List<Block> walls() {
        Block f1 = new Block(0, GameStandarts.BAR_SIZE, GameStandarts.WALL_SIZE,
                GameStandarts.HEIGHT, -1, Color.GRAY);
        Block f2 = new Block(0, GameStandarts.BAR_SIZE, GameStandarts.HEIGHT,
                GameStandarts.WALL_SIZE, -1, Color.GRAY);
        Block f3 = new Block(GameStandarts.HEIGHT - GameStandarts.WALL_SIZE,
                GameStandarts.WALL_SIZE, GameStandarts.WIDTH, GameStandarts.WALL_SIZE, -1, Color.GRAY);
        Block f4 = new Block(0, GameStandarts.WIDTH + 20,
                GameStandarts.WALL_SIZE, GameStandarts.HEIGHT, -1, Color.GRAY);
        f1.setBlockType(BlockType.Wall);
        f2.setBlockType(BlockType.Wall);
        f3.setBlockType(BlockType.Wall);
        f4.setBlockType(BlockType.Destroier);
        List<Block> w = new LinkedList<>();
        w.add(f1);
        w.add(f2);
        w.add(f3);
        w.add(f4);
        return w;
    }

    /**
     * block.
     *
     * @return list block
     */
    private List<Block> block() {
        List<Block> blocks = new LinkedList<>();
        int width = (GameStandarts.HEIGHT - 2 * GameStandarts.WALL_SIZE) / 14;
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK};
        for (int i = GameStandarts.WALL_SIZE, j = 0;
             i < GameStandarts.HEIGHT - GameStandarts.WALL_SIZE - width; i += width, j++) {
            Block b = new Block(i, 200, 30, width, 1, colors[j / 2]);
            b.setBlockType(BlockType.Normal);
            blocks.add(b);
            numberOfBlocks++;
        }
        return blocks;
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
        clonedLevel.setBackground(getBackground());
        List<Velocity> newVelocities = new LinkedList<>();
        for (Velocity v : initialBallVelocities()) {
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
