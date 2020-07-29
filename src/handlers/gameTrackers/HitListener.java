package handlers.gameTrackers;

import geometry.GameObjects.Ball;
import geometry.GameObjects.Block;

/**
 * HitListener.
 */
public interface HitListener {
    /**
     * hitEvent.
     * @param beingHit Block
     * @param hitter hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}