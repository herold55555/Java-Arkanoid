package handlers.gameManger;

import geometry.GameObjects.BlockType;
import graphics.bar.Bar;
import handlers.graphic.KeyPressStoppableAnimation;
import handlers.gameTrackers.BallRemover;
import handlers.gameTrackers.BlockRemover;
import handlers.collision.GameEnvironment;
import handlers.gameTrackers.Counter;
import settings.GameStandarts;
import settings.Logger;
import handlers.gameTrackers.ScoreTrackingListener;
import geometry.GameObjects.Ball;
import geometry.GameObjects.Block;
import geometry.GameObjects.Paddle;
import geometry.invisible.Velocity;
import handlers.graphic.AnimationRunner;
import graphics.screens.CountdownAnimation;
import graphics.screens.PauseScreen;
import handlers.graphic.SpriteCollection;
import handlers.graphic.Animation;
import handlers.collision.Collidable;
import graphics.levelData.LevelInformation;
import handlers.graphic.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Logger log;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private Counter livesCounter;
    private Paddle player;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInformation;

    /**
     * Constructor.
     *
     * @param levelInfo LevelInformation
     * @param rn        AnimationRunner
     * @param ks        KeyboardSensor
     * @param score     Counter
     * @param lives     Counter
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner rn, KeyboardSensor ks, Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.log = new Logger();
        blocksCounter = new Counter();
        ballsCounter = new Counter();
        scoreCounter = score;
        livesCounter = lives;
        runner = rn;
        keyboardSensor = ks;
        levelInformation = levelInfo;
    }

    /**
     * add collidiable object.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprie object.
     *
     * @param s sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removeCollidable.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removeSprite.
     *
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * initial game.
     */
    public void initialize() {
        sprites.addSprite(levelInformation.getBackground());
        initialPlayer();
        initialBlocks();
        initialWalls();
        initialBar();
        ballsCounter.increase(levelInformation.numberOfBalls());
    }

    /**
     * initialPlayer.
     */
    private void initialPlayer() {
        int speed = levelInformation.paddleSpeed();
        int width = levelInformation.paddleWidth();
        player = new Paddle(keyboardSensor, width, speed);
        player.addToGame(this);
        player.setLog(log);
    }

    /**
     * initialBlocks.
     */
    private void initialBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        blocksCounter.increase(levelInformation.numberOfBlocksToRemove());
        for (Block b : levelInformation.blocks()) {
            b.addToGame(this);
            b.setBlockType(BlockType.Normal);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * initial walls.
     */
    private void initialWalls() {
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        Block w1 = new Block(0, GameStandarts.BAR_SIZE, GameStandarts.WALL_SIZE,
                GameStandarts.HEIGHT, -1, Color.GRAY);
        Block w2 = new Block(0, GameStandarts.BAR_SIZE, GameStandarts.HEIGHT,
                GameStandarts.WALL_SIZE, -1, Color.GRAY);
        Block w3 = new Block(GameStandarts.HEIGHT - GameStandarts.WALL_SIZE,
                GameStandarts.WALL_SIZE, GameStandarts.WIDTH, GameStandarts.WALL_SIZE, -1, Color.GRAY);
        Block w4 = new Block(0, GameStandarts.WIDTH + 20,
                GameStandarts.WALL_SIZE, GameStandarts.HEIGHT, -1, Color.GRAY);
        w1.setBlockType(BlockType.Wall);
        w2.setBlockType(BlockType.Wall);
        w3.setBlockType(BlockType.Wall);
        w4.setBlockType(BlockType.Destroier);
        w1.addToGame(this);
        w2.addToGame(this);
        w3.addToGame(this);
        w4.addToGame(this);
        w4.addHitListener(ballRemover);
    }

    /**
     * initial balls.
     */
    public void addBalls() {
        int xLocation = GameStandarts.HEIGHT / (levelInformation.numberOfBalls() + 1);
        int skip = xLocation;
        for (Velocity velocity : levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(xLocation, GameStandarts.WIDTH - 50, GameStandarts.BALL_RADIUS, Color.white);
            ball.addToGame(this);
            ball.setGameEnvironment(environment);
            ball.setLog(log, 1);
            ball.setVelocity(velocity);
            xLocation += skip;
        }
        if (ballsCounter.getValue() != levelInformation.numberOfBalls()) {
            ballsCounter.increase(levelInformation.numberOfBalls());
        }
    }

    /**
     * initialBar.
     */
    private void initialBar() {
        Bar bar = new Bar(levelInformation.levelName(), scoreCounter, livesCounter);
        addSprite(bar);
    }

    /**
     * initialNewTurn.
     */
    private void initialNewTurn() {
        addBalls();
        player.resetPaddlePlace();
    }

    /**
     * playOneTurn.
     */
    public void playOneTurn() {
        initialNewTurn();
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.runner.run(this);
        if (blocksCounter.getValue() == 0) {
            scoreCounter.increase(100);
        }
    }

    /**
     * run.
     */
    public void run() {
        while (livesCounter.getValue() > 0) {
            playOneTurn();
            livesCounter.decrease(1);
            if (blocksCounter.getValue() == 0) {
                break;
            }
        }
        runner.close();
    }

    /**
     * doOneFrame.
     *
     * @param dt dt
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboardSensor,
                    "space", new PauseScreen()));
        }
        if (this.keyboardSensor.isPressed("c")) {
            log.close();
        }
    }

    /**
     * shouldStop.
     *
     * @return boolean
     */
    public boolean shouldStop() {
        if (blocksCounter.getValue() > 0 && ballsCounter.getValue() > 0) {
            return false;
        }
        return true;
    }

    /**
     * isLevelClear.
     *
     * @return boolean
     */
    public boolean isLevelClear() {
        return blocksCounter.getValue() == 0;
    }

    /**
     * get game environment.
     *
     * @return game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * get logger.
     *
     * @return logger
     */
    public Logger getLog() {
        return log;
    }

    /**
     * main.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
//        GameLevel gameLevel = new GameLevel(new LevelFinalFour());
//        gameLevel.run();
    }
}