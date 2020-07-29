package handlers.collision;

import geometry.GameObjects.Ball;
import geometry.visible.Rectangle;
import geometry.invisible.Point;
import geometry.invisible.Velocity;

/**
 * collidable interface.
 */
public interface Collidable {
    /**
     * get the rectangle of collidable.
     *
     * @return rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * hit object.
     *
     * @param hitter hitter
     * @param collisionPoint  collision point
     * @param currentVelocity velocity of hit
     * @return new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
