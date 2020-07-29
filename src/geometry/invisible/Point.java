package geometry.invisible;

/**
 * point object.
 */
public class Point {
    private double x;
    private double y;

    /**
     * contractor.
     *
     * @param x coord x
     * @param y corrd y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        if (other == null) {
            return Double.POSITIVE_INFINITY;
        }
        double dis = Math.pow((this.x - other.getX()), 2);
        dis += Math.pow((this.y - other.getY()), 2);
        dis = Math.sqrt(dis);
        return dis;
    }

    /**
     * size of point.
     *
     * @return distance for 0,0
     */
    public double size() {
        return distance(new Point(0, 0));
    }

    /**
     * check if points are equals.
     *
     * @param other other point
     * @return true if equals, otherwise false
     */
    public boolean equals(Point other) {
        if (distance(other) == 0) {
            return true;
        }
        return false;
    }

    /**
     * get x.
     *
     * @return x coord
     */
    public double getX() {
        return this.x;
    }

    /**
     * get y.
     *
     * @return y coord
     */
    public double getY() {
        return this.y;
    }

    /**
     * combine to points, with + -.
     * @param p another point
     * @param sign -1 or 1
     * @return new pont after add
     */
    public Point add(Point p, int sign) {
        return new Point(this.getX() + (p.getX() * sign), this.getY() + (p.getY() * sign));
    }
}