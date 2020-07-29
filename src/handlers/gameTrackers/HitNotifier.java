package handlers.gameTrackers;

/**
 * HitNotifier.
 */
public interface HitNotifier {
    /**
     * addHitListener.
     * @param hl HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener.
     * @param hl HitListener
     */
    void removeHitListener(HitListener hl);
}