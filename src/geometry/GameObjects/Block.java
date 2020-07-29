package geometry.GameObjects;

import geometry.visible.DrawableRectangle;
import geometry.visible.Line;
import geometry.visible.Rectangle;
import handlers.gameManger.GameLevel;
import handlers.levelParser.BackgroundFill;
import settings.Logger;
import geometry.invisible.Point;
import geometry.invisible.Velocity;
import handlers.collision.Collidable;
import handlers.gameTrackers.HitListener;
import handlers.gameTrackers.HitNotifier;
import handlers.graphic.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private DrawableRectangle rectangle;
    private List<HitListener> hitListeners;
    private int remainHits;
    private BlockType blockType;

    /**
     * set logger.
     *
     * @param l logger
     */
    public void setLog(Logger l) {
        rectangle.setLog(l);
    }

    /**
     * Constractor.
     *
     * @param p     upper left point
     * @param w     WIDTH
     * @param h     HEIGHT
     * @param hits  remains hits of block
     * @param color color
     */
    public Block(Point p, int w, int h, int hits, Color color) {
        this.rectangle = new DrawableRectangle(p, w, h, color);
        this.remainHits = hits;
        hitListeners = new LinkedList<>();
        blockType = BlockType.UnInitialized;
    }

    /**
     * Constractor.
     *
     * @param p     upper left point
     * @param w     WIDTH
     * @param h     HEIGHT
     * @param hits  remains hits of block
     * @param color color
     */
    public Block(Point p, int w, int h, int hits, BackgroundFill color) {
        this.rectangle = new DrawableRectangle(p, w, h, color);
        this.remainHits = hits;
        hitListeners = new LinkedList<>();
        blockType = BlockType.UnInitialized;
    }

    /**
     * Constractor.
     *
     * @param x     x of upper left point
     * @param y     y of upper left point
     * @param w     WIDTH
     * @param h     HEIGHT
     * @param hits  remains hits of block
     * @param color color
     */
    public Block(double x, double y, int w, int h, int hits, Color color) {
        this(new Point(x, y), w, h, hits, color);
    }

    /**
     * get height.
     * @return hieght
     */
    public double getHeight() {
        return rectangle.getHeight();
    }

    /**
     * return rectangle.
     *
     * @return this rectangle
     */
    public Rectangle getCollisionRectangle() {
        return rectangle.getCollisionRectangle();
    }

    /**
     * hit the block.
     *
     * @param hitter          hitter
     * @param collisionPoint  collision point
     * @param currentVelocity hit with the velocity of...
     * @return new velocity according to the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int corner = 0;
        hitter.setHited();
        notifyHit(hitter);

        Line hitLine = createLineOfHit(collisionPoint, currentVelocity);
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        List<Line> frameLines = rectangle.getRectangleLines();
        for (Line l : frameLines) {
            Velocity tmp = new Velocity(l.end().add(l.start(), -1));
            if (l.isIntersecting(hitLine) && currentVelocity.cross(tmp) < 0) {
                corner = 1;
                if (l.tilt() == 0) {
                    dy *= -1;
                } else {
                    dx *= -1;
                }
            }
        }
        if (corner == 1) {
            return new Velocity(dx, dy);
        }
        Point minP = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Point p : this.rectangle.getCornerPoints()) {
            if (collisionPoint.distance(p) < minDist) {
                minDist = collisionPoint.distance(p);
                minP = p;
            }
        }
        double x = hitLine.start().getX() - minP.getX();
        double y = hitLine.start().getY() - minP.getY();
        double c = -2 * (currentVelocity.getDx() * x + currentVelocity.getDy() * y) / (x * x + y * y);
        double ballDx = currentVelocity.getDx() + c * x;
        double ballDy = currentVelocity.getDy() + c * y;

        return new Velocity(ballDx, ballDy);
    }

    /**
     * draw the block.
     *
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d, "" + remainHits);
    }

    /**
     * time passed.
     * @param dt dt
     */
    public void timePassed(double dt) {

    }

    /**
     * remove from game.
     *
     * @param gameLevel game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * create hit line.
     *
     * @param p point
     * @param v velocity
     * @return line according to the velocity.
     */
    private Line createLineOfHit(Point p, Velocity v) {
        return new Line(v.multiple(-1).applyToPoint(p), p);
    }

    /**
     * add block to game.
     *
     * @param g game to add the block to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * add listner.
     *
     * @param hl HitListener
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * remover listner.
     *
     * @param hl HitListener
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify hit.
     *
     * @param hitter hitter.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new LinkedList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * shoot block.
     */
    public void shootBlock() {
        remainHits--;
    }

    /**
     * get remains hit.
     *
     * @return hits
     */
    public int getRemainHits() {
        return remainHits;
    }

    /**
     * block type.
     *
     * @return vlock type
     */
    public BlockType getBlockType() {
        return blockType;
    }

    /**
     * set block type.
     *
     * @param bT block type.
     */
    public void setBlockType(BlockType bT) {
        blockType = bT;
    }

    /**
     * clone.
     * @return clone.
     */
    public Block clone() {
        Point upperLeft = rectangle.getUpperLeft();
        int width = (int) rectangle.getWidth();
        int hieght = (int) rectangle.getHeight();
        BackgroundFill backgroundFill = rectangle.getBackgroundFill();
        Block clonedBlock = new Block(upperLeft, width, hieght, remainHits, backgroundFill);
        for (HitListener listener : hitListeners) {
            clonedBlock.addHitListener(listener);
        }
        return clonedBlock;
    }
}
