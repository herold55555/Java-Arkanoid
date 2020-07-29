package handlers.graphic;

import biuoop.DrawSurface;

/**
 * sprite.
 */
public interface Sprite {
    /**
     * draw object.
     *
     * @param surface draw surface
     */
    void drawOn(DrawSurface surface);

    /**
     * alart that time passed.
     *
     * @param dt dt
     */
    void timePassed(double dt);
}
