package graphics.levelData;

import geometry.GameObjects.BlockType;
import geometry.visible.DrawableRectangle;
import handlers.levelParser.Level;
import settings.GameStandarts;
import geometry.invisible.Point;
import geometry.invisible.Velocity;
import geometry.GameObjects.Block;
import geometry.visible.Circle;
import graphics.background.Background;
import handlers.graphic.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * LevelGreen3.
 */
public class LevelGreen3 implements LevelInformation {

    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blockInGame;
    private String levelName;
    private int numberOfBlocks;

    /**
     * constructor.
     */
    public LevelGreen3() {
        numberOfBalls = 2;
        paddleSpeed = 2;
        numberOfBlocks = 0;
        paddleWidth = 80;
        levelName = "Green 3";
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
        for (int i = 0; i < 2; i++) {
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
                GameStandarts.HEIGHT, new Color(0, 102, 0));
        b.addShape(rectangle);
        int x = 60;
        int y = GameStandarts.WIDTH - 175;
        DrawableRectangle r1 = new DrawableRectangle(new Point(x, y), 175, 95, Color.DARK_GRAY);
        b.addShape(r1);
        for (int i = x + 10; i < 145; i += 20) {
            for (int j = y + 10; j < GameStandarts.WIDTH; j += 35) {
                DrawableRectangle r = new DrawableRectangle(new Point(i, j), 25, 15, Color.WHITE);
                b.addShape(r);
            }
        }
        DrawableRectangle poll = new DrawableRectangle(new Point(x + 42.5, y - 220), 220, 10, Color.DARK_GRAY);
        b.addShape(poll);
        Color newYellow = new Color(255, 235, 153);
        Circle c1 = new Circle(x + 47, y - 220, 10, newYellow);
        Circle c2 = new Circle(x + 47, y - 220, 8, Color.RED);
        Circle c3 = new Circle(x + 47, y - 220, 4, Color.WHITE);
        b.addShape(c1);
        b.addShape(c2);
        b.addShape(c3);
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
        List<Block> blocks = new LinkedList<>();
        int width = 30;
        int height = 60;
        int numOfRow = 5;
        int numOfBlocks = 8;
        Color[] colors = {new Color(0, 255, 0), new Color(51, 255, 51),
                new Color(102, 255, 102), new Color(153, 255, 153),
                new Color(204, 255, 204)};
        for (int i = 140; i < width * numOfRow + 140; i = i + width, numOfBlocks--) {
            for (int j = GameStandarts.HEIGHT - GameStandarts.WALL_SIZE - height - 1;
                 j > 60 * (9 - numOfBlocks); j -= height) {
                int hit = 1;
                if (i == 140) {
                    hit = 2;
                }
                Block b = new Block(j, i, width, height, hit, colors[8 - numOfBlocks]);
                b.setBlockType(BlockType.Normal);
                blocks.add(b);
                numberOfBlocks++;
            }
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
