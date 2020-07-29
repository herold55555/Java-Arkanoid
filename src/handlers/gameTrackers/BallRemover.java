package handlers.gameTrackers;

import geometry.GameObjects.BlockType;
import handlers.gameManger.GameLevel;
import geometry.GameObjects.Ball;
import geometry.GameObjects.Block;

/**
 * BallRemover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * BallRemover.
     * @param gameLevel GameLevel
     * @param removedBlocks Counter
     */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBlocks;
    }

    /**
     * hitEvent.
     * @param beingHit Block
     * @param hitter hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getBlockType() == BlockType.Destroier) {
            remainingBalls.decrease(1);
            hitter.removeFromGame(gameLevel);
        }
    }
}
