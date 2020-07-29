package handlers.gameTrackers;

import geometry.GameObjects.BlockType;
import geometry.GameObjects.Ball;
import geometry.GameObjects.Block;

/**
 * ScoreTrackingListener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constractor.
     * @param scoreCounter counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hit event.
     * @param beingHit Block
     * @param hitter hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getBlockType() == BlockType.Normal) {
            if (beingHit.getRemainHits() == 0) {
                currentScore.increase(10);
            }
            currentScore.increase(5);
        }
    }
}