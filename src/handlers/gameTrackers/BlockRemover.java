package handlers.gameTrackers;

import geometry.GameObjects.BlockType;
import handlers.gameManger.GameLevel;
import geometry.GameObjects.Ball;
import geometry.GameObjects.Block;

/**
 * blockremover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * blockremover.
     * @param gameLevel game gameManger
     * @param removedBlocks counter
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hit event.
     * @param beingHit Block
     * @param hitter hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getBlockType() == BlockType.Normal) {
            beingHit.shootBlock();
            if (beingHit.getRemainHits() == 0) {
                beingHit.removeHitListener(this);
                beingHit.removeFromGame(gameLevel);
                remainingBlocks.decrease(1);
            }
        }
    }
}