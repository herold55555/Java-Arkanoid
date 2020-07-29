package geometry.invisible;

/**
 * velocity object.
 */
public class Velocity {
    private double dx;
    private double dy;
    private double angle;
    private double speed;

    /**
     * constractor.
     *
     * @param dx x coord of vector
     * @param dy y coord of vector
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.speed = Math.sqrt(dx * dx + dy * dy);
        this.angle = Math.toDegrees(Math.atan2(dx, dy));
    }

    /**
     * Constractor.
     *
     * @param p point
     */
    public Velocity(Point p) {
        this(p.getX(), p.getY());
    }

    /**
     * contractor by angle and speed.
     *
     * @param angle direction.
     * @param speed speed.
     * @return new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));

        return new Velocity(dx, dy);
    }

    /**
     * get dx.
     *
     * @return dx
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * get dy.
     *
     * @return dy
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get angle.
     *
     * @return angle
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * get speed.
     *
     * @return speed
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * return another point, with this velocity change.
     *
     * @param p point to change
     * @return new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * add between velocities.
     *
     * @param v1 another velocity
     * @return new velocity.
     */
    public Velocity add(Velocity v1) {
        Velocity v = new Velocity(this.dx + v1.getDx(), this.dy + v1.getDy());
        return v;
    }

    /**
     * multiply between velocity and number.
     *
     * @param n number to multiply
     * @return new velocity.
     */
    public Velocity multiple(double n) {
        Velocity v = new Velocity(this.dx * n, this.dy * n);
        return v;
    }

    /**
     * cross vector.
     *
     * @param v other vector
     * @return the answer
     */
    public double cross(Velocity v) {
        return (this.getDx() * v.getDy()) - (this.getDy() * v.getDx());
    }

    /**
     * dot vector.
     *
     * @param v another vector
     * @return the answer
     */
    public double dot(Velocity v) {
        return this.getDx() * v.getDx() + this.getDy() * v.getDy();
    }

    /**
     * compare 2 velocities.
     *
     * @param v another velocity
     * @return check if they equals
     */
    public boolean compare(Velocity v) {
        if (Math.abs(this.dx - v.dx) < 0.001 && Math.abs(this.dy - v.dy) < 0.001) {
            return true;
        }
        return false;
    }

    /**
     * clone.
     * @return clone
     */
    public Velocity clone() {
        Velocity clonedVelocity = new Velocity(dx, dy);
        return clonedVelocity;
    }
}