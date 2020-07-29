package play;

import handlers.gameManger.GameLevel;
import geometry.GameObjects.Ball;

import java.awt.Color;

/**
 * tests.
 */
public class Tests {

    /**
     * test 1.
     * @param gameLevel gameLevel
     */
    public static void test1(GameLevel gameLevel) {
        //paddle at 532, 575
        Ball ball1 = new Ball(537.297585, 601.13458, 10, Color.white);
        ball1.addToGame(gameLevel);
        ball1.setGameEnvironment(gameLevel.getEnvironment());
        ball1.setLog(gameLevel.getLog(), 1);
        ball1.setVelocity(2.7771797894170875, -1.1345802824186848);
    }

    /**
     * test 2.
     * @param gameLevel gameLevel
     */
    public static void test2(GameLevel gameLevel) {
        //paddle at 368, 575
        Ball ball1 = new Ball(381.7292288851621 + (-1.0920277939171876), 600.0 + (-2.794185981160231),
                10, Color.white);
        ball1.addToGame(gameLevel);
        ball1.setGameEnvironment(gameLevel.getEnvironment());
        ball1.setLog(gameLevel.getLog(), 1);
        ball1.setVelocity(-1.0920277939171876, -2.794185981160231);
    }

    /**
     * test 3.
     * @param gameLevel gameLevel
     */
    public static void test3(GameLevel gameLevel) {
        Ball ball1 = new Ball(188.00146556998834 - (-2.672517720570306),
                574.8288005494412 - (1.3629559909394375), 10, Color.white);
        ball1.addToGame(gameLevel);
        ball1.setGameEnvironment(gameLevel.getEnvironment());
        ball1.setLog(gameLevel.getLog(), 1);
        ball1.setVelocity(-2.672517720570306, 1.3629559909394375);

    }

    /**
     * main.
     * @param args arguments
     */
    public static void main(String[] args) {
//        GameLevel game = new GameLevel(640);
//        game.initialize();

    }
}
