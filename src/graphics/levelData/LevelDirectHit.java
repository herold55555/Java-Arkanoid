package graphics.levelData;

import geometry.GameObjects.BlockType;
import geometry.visible.DrawableRectangle;
import geometry.visible.Circle;
import geometry.visible.DrawableLine;
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
 * LevelDirectHit.
 */
public class LevelDirectHit implements LevelInformation {

    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blockInGame;
    private String levelName;
    private int numberOfBlocks;

    /**
     * constructor.
     */
    public LevelDirectHit() {
        numberOfBalls = 1;
        paddleSpeed = 2;
        numberOfBlocks = 0;
        paddleWidth = 80;
        levelName = "Direct Hit";
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
        int angle = random.nextInt(121) + 120;
        velocities.add(Velocity.fromAngleAndSpeed(angle, GameStandarts.BALL_SPEED));
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
        DrawableRectangle rectangle = new DrawableRectangle(new Point(0, 0),
                GameStandarts.WIDTH, GameStandarts.HEIGHT, Color.BLACK);
        int x = GameStandarts.HEIGHT / 2;
        int y = 215;
        Circle c1 = new Circle(new Point(x, y), 100);
        Circle c2 = new Circle(new Point(x, y), 75);
        Circle c3 = new Circle(new Point(x, y), 50);
        c1.addColor(Color.BLUE, "s");
        c2.addColor(Color.BLUE, "s");
        c3.addColor(Color.BLUE, "s");
        DrawableLine l1 = new DrawableLine(x, y - 125, x, y + 125, Color.BLUE);
        DrawableLine l2 = new DrawableLine(x - 125, y, x + 125, y, Color.BLUE);
        b.addShape(rectangle);
        b.addShape(c1);
        b.addShape(c2);
        b.addShape(c3);
        b.addShape(l1);
        b.addShape(l2);
        return b;
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
     * blocks.
     *
     * @return list blocks
     */
    public List<Block> blocks() {
        return blockInGame;
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
        int x = GameStandarts.HEIGHT / 2 - 15;
        Block b = new Block(x, 200, 30, 30, 1, Color.red);
        b.setBlockType(BlockType.Normal);
        List<Block> blocks = new LinkedList<>();
        blocks.add(b);
        numberOfBlocks++;
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
