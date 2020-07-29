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
 * LevelFinalFour.
 */
public class LevelFinalFour implements LevelInformation {

    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blockInGame;
    private String levelName;
    private int numberOfBlocks;

    /**
     * constructor.
     */
    public LevelFinalFour() {
        numberOfBalls = 3;
        paddleSpeed = 3;
        numberOfBlocks = 0;
        paddleWidth = 85;
        levelName = "Final Four";
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
        for (int i = 0; i < 3; i++) {
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
        DrawableRectangle rectangle = new DrawableRectangle(new Point(0, 0), GameStandarts.WIDTH, GameStandarts.HEIGHT,
                new Color(0, 64, 255));
        b.addShape(rectangle);

        int x = GameStandarts.HEIGHT / 2 + 30;
        int y = 485;
        Circle c1 = new Circle(x, y, 290, Color.RED);
        Circle c2 = new Circle(x, y, 280, Color.ORANGE);
        Circle c3 = new Circle(x, y, 270, Color.YELLOW);
        Circle c4 = new Circle(x, y, 260, Color.GREEN);
        Circle c5 = new Circle(x, y, 250, Color.CYAN);
        Circle c6 = new Circle(x, y, 240, Color.BLUE);
        Circle c7 = new Circle(x, y, 230, new Color(153, 0, 153));
        Circle c8 = new Circle(x, y, 220, new Color(0, 64, 255));
        b.addShape(c1);
        b.addShape(c2);
        b.addShape(c3);
        b.addShape(c4);
        b.addShape(c5);
        b.addShape(c6);
        b.addShape(c7);
        b.addShape(c8);
        DrawableRectangle rec1 = new DrawableRectangle(new Point(0, y), GameStandarts.WIDTH - y, GameStandarts.HEIGHT,
                new Color(0, 64, 255));
        b.addShape(rec1);


        for (int i = 140; i < 230; i += 7) {
            DrawableLine l = new DrawableLine(i, 450, i - 20, GameStandarts.WIDTH, Color.WHITE);
            b.addShape(l);
        }
        Circle c11 = new Circle(150, 450 + 10, 25, new Color(179, 179, 179));
        Circle c12 = new Circle(165, 465 + 10, 23, new Color(179, 179, 179));
        Circle c13 = new Circle(180, 435 + 10, 27, new Color(153, 153, 153));
        Circle c14 = new Circle(195, 460 + 10, 20, new Color(128, 128, 128));
        Circle c15 = new Circle(210, 445 + 10, 25, new Color(128, 128, 128));
        b.addShape(c11);
        b.addShape(c12);
        b.addShape(c13);
        b.addShape(c14);
        b.addShape(c15);

        for (int i = 645; i < 725; i += 7) {
            DrawableLine l = new DrawableLine(i, 500, i - 20, GameStandarts.WIDTH, Color.WHITE);
            b.addShape(l);
        }
        Circle c21 = new Circle(655, 480, 25, new Color(179, 179, 179));
        Circle c22 = new Circle(665, 515, 23, new Color(179, 179, 179));
        Circle c23 = new Circle(685, 495, 27, new Color(153, 153, 153));
        Circle c24 = new Circle(690, 520, 20, new Color(128, 128, 128));
        Circle c25 = new Circle(715, 505, 25, new Color(128, 128, 128));
        b.addShape(c21);
        b.addShape(c22);
        b.addShape(c23);
        b.addShape(c24);
        b.addShape(c25);

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
        int height = (GameStandarts.HEIGHT - 2 * GameStandarts.WALL_SIZE) / 15;
        int width = 25;
        int numOfRow = 6;
        int colorIndex = 0;
        Color[] colors = {Color.DARK_GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.PINK, Color.CYAN};
        for (int j = 120; j < numOfRow * width + 120; j += width, colorIndex++) {
            for (int i = GameStandarts.WALL_SIZE + 4;
                 i < GameStandarts.HEIGHT - height - GameStandarts.WALL_SIZE; i += height) {
                int hit = 1;
                if (j == 120) {
                    hit = 2;
                }
                Block b = new Block(i, j, width, height, hit, colors[colorIndex]);
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
