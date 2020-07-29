package handlers.collision;

import geometry.invisible.Velocity;
import geometry.GameObjects.Ball;
import geometry.visible.Line;
import geometry.GameObjects.Paddle;
import geometry.invisible.Point;
import geometry.visible.Rectangle;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class GameEnvironment {
    private List<Collidable> objects;
//    private Paddle paddle;

    /**
     * Constractor.
     *
     * @param objects list of collidable objects
     * @param p       collidable paddle
     */
    public GameEnvironment(List<Collidable> objects, Paddle p) {
        this.objects = objects;
//        this.paddle = p;
    }

    /**
     * Constractor.
     *
     * @param p paddle
     */
    public GameEnvironment(Paddle p) {
        this(new LinkedList<>(), p);
    }

    /**
     * Constractor.
     */
    public GameEnvironment() {
        this(new LinkedList<>(), null);
    }

    /**
     * move ball.
     *
     * @param ball ball
     */
    public void moveBallTo(Ball ball) {
        Line movement = new Line(ball.getCenter(),
                ball.getVelocity().applyToPoint(ball.getCenter()));
        List<CollisionInfo> collisionInfo = checkObjectCollision(movement);
        if (collisionInfo.isEmpty()) {
            ball.setUnHited();
            ball.moveOneStepWithCurrentVelocity();
            return;
        }
        if (collisionInfo.size() > 1) {
            for (CollisionInfo cI : collisionInfo) {
                if (cI.collisionObject() instanceof Paddle) {
                    collisionInfo.remove(cI);
                }
            }
        }
        double restDist = ball.getVelocity().getSpeed()
                - collisionInfo.get(0).collisionPoint().distance(ball.getCenter()) + 0.01;
        Velocity currentVelocity = ball.getVelocity();
        if (restDist < 0) {
            restDist = currentVelocity.getSpeed();
        }
        for (CollisionInfo cI : collisionInfo) {
            if (cI.equals(ball.getCollidable()) && ball.checkUnhited()) {
                continue;
            }
            ball.setLastCollidable(cI.collisionObject());
            currentVelocity = cI.collisionObject().hit(ball, cI.collisionPoint(), currentVelocity);
        }
        if (!currentVelocity.compare(ball.getVelocity())) {
            currentVelocity = checkIfStuck(currentVelocity);
            ball.setVelocity(currentVelocity);
            ball.moveOneStep(collisionInfo.get(0).collisionPoint());
            ball.moveOneStep(Velocity.fromAngleAndSpeed(ball.getVelocity().getAngle(), restDist)
                    .applyToPoint(ball.getCenter()));
        } else {
            ball.moveOneStepWithCurrentVelocity();
        }
    }

    /**
     * check for collision with ball movment.
     *
     * @param movement movment of ball
     * @return list of collisions
     */
    private List<CollisionInfo> checkObjectCollision(Line movement) {
        List<CollisionInfo> collisionInfo = getClosestCollisionList(movement);
        return collisionInfo;
    }

    /**
     * get the closest collision.
     *
     * @param trajectory movment line
     * @return list of collitions
     */
    public List<CollisionInfo> getClosestCollisionList(Line trajectory) {
        List<CollisionInfo> hits = new LinkedList<>();
        double minDist = Double.POSITIVE_INFINITY;
        for (Collidable obj : this.objects) {
            Rectangle rec = obj.getCollisionRectangle();
            Point tmp = trajectory.closestIntersectionToStartOfLine(rec);
            if (trajectory.start().distance(tmp) < minDist) {
                minDist = trajectory.start().distance(tmp);
                hits.clear();
                hits.add(new CollisionInfo(tmp, obj));
            } else if (trajectory.start().distance(tmp) == minDist && minDist != Double.POSITIVE_INFINITY) {
                hits.add(new CollisionInfo(tmp, obj));
            }
        }
        return hits;
    }

    /**
     * get the closest collision.
     *
     * @param trajectory movment line
     * @return collition
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo hit = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Collidable obj : this.objects) {
            Rectangle rec = obj.getCollisionRectangle();
            Point tmp = trajectory.closestIntersectionToStartOfLine(rec);
            if (trajectory.start().distance(tmp) < minDist) {
                minDist = trajectory.start().distance(tmp);
                hit = new CollisionInfo(tmp, obj);
            }
        }
        return hit;
    }

    /**
     * add collidable to list.
     *
     * @param c collidable objects
     */
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    /**
     * removeCollidable.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        objects.remove(c);
    }

    /**
     * checkIfStuck.
     *
     * @param v Velocity
     * @return Velocity
     */
    private Velocity checkIfStuck(Velocity v) {
        if (Math.abs(v.getDy()) <= 0.000001) {
            double dy = -0.15;
            double dx = Math.sqrt((v.getDx() * v.getDx()) - (dy * dy));
            if (v.getDx() < 0) {
                dx *= -1;
            }
            return new Velocity(dx, dy);
        }
        return v;
    }
}
