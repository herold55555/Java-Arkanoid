package geometry.GameObjects;

import geometry.visible.DrawableRectangle;
import geometry.visible.Line;
import geometry.visible.Rectangle;
import handlers.gameManger.GameLevel;
import handlers.levelParser.BackgroundFill;
import settings.GameStandarts;
import settings.Logger;
import geometry.invisible.Point;
import geometry.invisible.Velocity;
import handlers.collision.Collidable;
import handlers.graphic.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * paddle.
 */
public class Paddle implements Sprite, Collidable {
    private DrawableRectangle rectangle;
    private KeyboardSensor keyboard;
    private int speed;
    private Point resetPoint;
    private Logger log;

    /**
     * set logger.
     *
     * @param l logger
     */
    public void setLog(Logger l) {
        log = l;
        rectangle.setLog(l);
    }

    /**
     * write to log.
     */
    private void writeLog() {
        String txt = "Paddle:\nTop Left Corner: (" + rectangle.getUpperLeft().getX() + ","
                + rectangle.getUpperLeft().getY() + ")\n";
        log.write(txt);
    }

    /**
     * Constructor.
     *
     * @param keyboardS keyboard sensor
     * @param width     width
     * @param s         speed
     */
    public Paddle(KeyboardSensor keyboardS, int width, int s) {
        this.keyboard = keyboardS;
        resetPoint = new Point((GameStandarts.HEIGHT - width) / 2, GameStandarts.WIDTH - 30);
        BackgroundFill backgroundFill = new BackgroundFill();
        backgroundFill.addColor(Color.WHITE, "d");
        backgroundFill.addColor(Color.BLACK, "s");
        this.rectangle = new DrawableRectangle(resetPoint, 15, width, backgroundFill);
        speed = s;

    }

    /**
     * move paddle.
     *
     * @param dt dt
     */
    public void moveLeft(double dt) {
        Point upperLeft = new Point(rectangle.getUpperLeft().getX() - (speed * dt), rectangle.getUpperLeft().getY());
        rectangle.setUpperLeft(upperLeft);
    }

    /**
     * move paddle.
     *
     * @param dt dt
     */
    public void moveRight(double dt) {
        Point upperLeft = new Point(rectangle.getUpperLeft().getX() + (speed * dt), rectangle.getUpperLeft().getY());
        rectangle.setUpperLeft(upperLeft);
    }

    /**
     * move paddle.
     *
     * @param dt dt
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && rectangle.getUpperLeft().getX() > GameStandarts.WALL_SIZE) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && rectangle.getUpperLeft().getX() + rectangle.getHeight()
                < GameStandarts.HEIGHT - GameStandarts.WALL_SIZE) {
            moveRight(dt);
        }
    }

    /**
     * draw paddle.
     *
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d);
    }

    /**
     * return rectangle.
     *
     * @return rectangle of paddle
     */
    public Rectangle getCollisionRectangle() {
        return rectangle.getCollisionRectangle();
    }

    /**
     * hit the paddle.
     *
     * @param hitter          hitter
     * @param collisionPoint  collision point
     * @param currentVelocity velocity of hit
     * @return new velocity according to hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        hitter.writeLog();
        writeLog();
        int corner = 0;
        hitter.setHited();
        int funVelocity = getFunVelocity(collisionPoint);
        Velocity checkifNeedToChange = checkNeedToChange(collisionPoint, currentVelocity);
        if (checkifNeedToChange != null) {
            return checkifNeedToChange;
        }
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
            Velocity v = new Velocity(dx, dy);
            double newAngle = v.getAngle() + funVelocity;
            if (Math.cos(Math.toRadians(newAngle)) >= 0) {
                if (Math.sin(Math.toRadians(newAngle)) >= 0) {
                    newAngle = 90;
                } else {
                    newAngle = -90;
                }
            }
            return Velocity.fromAngleAndSpeed(newAngle, v.getSpeed());
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
     * velocity of fun paddle.
     *
     * @param point collision point
     * @return change angle.
     */
    private int getFunVelocity(Point point) {
        int[] change = {60, 30, 0, -30, -60};
        int index = (int) (5 * (point.getX() - rectangle.getUpperLeft().getX())
                / rectangle.getHeight());
        if (index < 0) {
            index = 0;
        }
        if (index > 4) {
            index = 4;
        }
        if (rectangle.getUpperLeft().getY() > point.getY()) {
            return change[index];
        }
        return -1 * change[index];
    }

    /**
     * create line of hit.
     *
     * @param p point of collision
     * @param v velocity of hit
     * @return line of hit
     */
    private Line createLineOfHit(Point p, Velocity v) {
        return new Line(v.multiple(-1).applyToPoint(p), p);
    }

    /**
     * check if need to change after hit.
     *
     * @param collitionPoint collision point
     * @param current        current velocit
     * @return velocity or null.
     */
    private Velocity checkNeedToChange(Point collitionPoint, Velocity current) {
        if (collitionPoint.getY() > rectangle.getUpperLeft().getY()
                && collitionPoint.getY() < rectangle.getUpperLeft().getY() + rectangle.getWidth()) {
            if (collitionPoint.getX() < rectangle.getUpperLeft().getX()) {
                return Velocity.fromAngleAndSpeed(-70, current.getSpeed());
            }
            return Velocity.fromAngleAndSpeed(70, current.getSpeed());
        }
        int fun = getFunVelocity(collitionPoint);
        if (fun != 0) {
            return Velocity.fromAngleAndSpeed(180 + fun, current.getSpeed());
        }
        return null;
    }

    /**
     * add paddle to game.
     *
     * @param g game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * reset paddle location.
     */
    public void resetPaddlePlace() {
        rectangle.setUpperLeft(resetPoint);
    }
}