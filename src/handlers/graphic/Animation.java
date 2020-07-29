package handlers.graphic;

import biuoop.DrawSurface;

/**
 * Animation.
 */
public interface Animation {
    /**
     * doOneFrame.
     *
     * @param d  DrawSurface
     * @param dt dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop.
     *
     * @return shouldStop
     */
    boolean shouldStop();
}
