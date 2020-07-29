package geometry.GameObjects;

import geometry.visible.Circle;
import handlers.collision.CollisionHandler;
import handlers.collision.GameEnvironment;
import handlers.gameManger.GameLevel;
import handlers.levelParser.BackgroundFill;
import settings.Logger;
import geometry.invisible.Point;
import geometry.invisible.Velocity;
import handlers.collision.Collidable;
import handlers.graphic.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ball object.
 */
public class Ball implements Sprite {
    private Circle circle;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private Logger log;
    private int ballNum;
    private CollisionHandler collisionHandler;

    /**
     * set logger.
     *
     * @param l   logger
     * @param num number of ball to write
     */
    public void setLog(Logger l, int num) {
        log = l;
        ballNum = num;
    }

    /**
     * write log message.
     */
    public void writeLog() {
        String txt = "Ball num " + ballNum + ":\nCenter: (" + circle.getX() + "," + circle.getY() + ")\n"
                + "Velocity: (" + v.getDx() + "," + v.getDy() + ")\n";
        log.write(txt);
    }

    /**
     * main constructor.
     *
     * @param center center point
     * @param r      radius of ball
     * @param color  color of ball
     */
    public Ball(Point center, int r, Color color) {
        BackgroundFill backgroundFill = new BackgroundFill();
        backgroundFill.addColor(color, "d");
        backgroundFill.addColor(Color.BLACK, "s");
        circle = new Circle(center, r, backgroundFill);
        this.v = new Velocity(0, 0);
        collisionHandler = new CollisionHandler();
    }

    /**
     * constructor.
     *
     * @param x     x coord of point
     * @param y     y coord of point
     * @param r     radius of ball
     * @param color color of ball
     */
    public Ball(double x, double y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * get center point.
     *
     * @return center point
     */
    public Point getCenter() {
        return circle.getCenter();
    }

    /**
     * get x coord of point.
     *
     * @return x coord
     */
    public int getX() {
        return circle.getX();
    }

    /**
     * get y coord of point.
     *
     * @return y coord
     */
    public int getY() {
        return circle.getY();
    }

    /**
     * get radius of ball.
     *
     * @return radius
     */
    public int getSize() {
        return circle.getSize();
    }

    /**
     * get color.
     *
     * @return color
     */
    public Color getColor() {
        return circle.getColor();
    }

    /**
     * get hit.
     *
     * @return boolean
     */
    public boolean getHit() {
        return collisionHandler.getHit();
    }

    /**
     * set hit.
     */
    public void setHited() {
        collisionHandler.setHit();
    }

    /**
     * set unhit.
     */
    public void setUnHited() {
        collisionHandler.setUnhit();
    }

    /**
     * check un hit.
     *
     * @return boolean
     */
    public boolean checkUnhited() {
        return collisionHandler.checkOutOfRange(circle.getCenter());
    }

    /**
     * set collidable.
     *
     * @param c collidable
     */
    public void setLastCollidable(Collidable c) {
        collisionHandler.setNewCollidable(c);
    }

    /**
     * get collidable.
     *
     * @return collidable
     */
    public Collidable getCollidable() {
        return collisionHandler.getCollidable();
    }

    /**
     * get velocity.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * draw thw ball.
     *
     * @param surface draw surface
     */
    public void drawOn(DrawSurface surface) {
        circle.drawOn(surface);
    }

    /**
     * set velocity.
     *
     * @param v1 velocity
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
        //writeLog();
    }

    /**
     * set velocity by points.
     *
     * @param dx dx of velocity
     * @param dy dy of velocity
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * move one step, and check collisions.
     *
     * @param dt dt
     */
    public void moveOneStep(double dt) {
        setVelocity(Velocity.fromAngleAndSpeed(v.getAngle(), v.getSpeed() * dt));
        gameEnvironment.moveBallTo(this);
        setVelocity(Velocity.fromAngleAndSpeed(v.getAngle(), v.getSpeed() / dt));
    }

    /**
     * move on step, with current velocity.
     */
    public void moveOneStepWithCurrentVelocity() {
        circle.setCenter(getVelocity().applyToPoint(circle.getCenter()));
    }

    /**
     * movr to point.
     *
     * @param next point to move to
     */
    public void moveOneStep(Point next) {
        circle.setCenter(getVelocity().applyToPoint(next));
    }

    /**
     * move one step, and check collisions.
     *
     * @param dt dt
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * add ball to game.
     *
     * @param g game to add the ball
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove from game.
     *
     * @param g game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * set game environment.
     *
     * @param ge new game environment
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }
}