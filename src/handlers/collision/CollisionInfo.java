package handlers.collision;

import geometry.invisible.Point;

/**
 * collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constractor.
     *
     * @param collisionP collision point
     * @param obj        collision object
     */
    public CollisionInfo(Point collisionP, Collidable obj) {
        this.collisionPoint = collisionP;
        this.collisionObject = obj;
    }

    /**
     * get collision point.
     *
     * @return collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * get collision object.
     *
     * @return colidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
