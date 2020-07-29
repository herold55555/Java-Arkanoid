package handlers.collision;

import geometry.invisible.Point;
import geometry.visible.Rectangle;

/**
 * CollisionHandler.
 */
public class CollisionHandler {
    private Collidable obj;
    private boolean hit;

    /**
     * Constractor.
     */
    public CollisionHandler() {
        obj = null;
        hit = false;
    }

    /**
     * setNewCollidable.
     * @param c Collidable
     */
    public void setNewCollidable(Collidable c) {
        obj = c;
    }

    /**
     * checkOutOfRange.
     * @param point point
     * @return boolean
     */
    public boolean checkOutOfRange(Point point) {
        if (obj == null) {
            hit = false;
            return hit;
        }
        Rectangle rect = obj.getCollisionRectangle();
        double r = rect.getOffset();
        double p = rect.getWidth() / 2;
        if (rect.distanceFromCorners(point) > Math.sqrt(4 * r * r + 4 * p * p)) {
            hit = false;
            return hit;
        }
        hit = true;
        return hit;
    }

    /**
     * setHit.
     */
    public void setHit() {
        hit = true;
    }

    /**
     * getHit.
     * @return hit
     */
    public boolean getHit() {
        return hit;
    }

    /**
     * setUnHit.
     */
    public void setUnhit() {
        hit = false;
        obj = null;
    }

    /**
     * getCollidable.
     * @return Collidable
     */
    public Collidable getCollidable() {
        return this.obj;
    }
}
