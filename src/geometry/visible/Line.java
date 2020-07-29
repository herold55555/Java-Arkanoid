package geometry.visible;

import geometry.invisible.Point;

import java.util.List;

/**
 * Line object.
 */
public class Line {
    private Point start;
    private Point end;
    private double tilt;
    private double b;

    /**
     * Constractor.
     *
     * @param start start point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        calcTilt();
        calcB();
    }

    /**
     * Constractor by x,y.
     *
     * @param x1 point 1
     * @param y1 point 1
     * @param x2 point 2
     * @param y2 point 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * calculate tilt.
     */
    private void calcTilt() {
        double mechane = this.start.getX() - this.end.getX();
        if (mechane == 0) {
            this.tilt = Double.POSITIVE_INFINITY;
        } else {
            this.tilt = (this.start.getY() - this.end.getY()) / mechane;
        }
    }

    /**
     * claculate parameter in line.
     * b in y = mx + b
     */
    private void calcB() {
        if (this.tilt == Double.POSITIVE_INFINITY) {
            this.b = this.start.getX();
        } else {
            this.b = this.start.getY() - (this.start.getX() * this.tilt);
        }
    }

    /**
     * lenght of line.
     *
     * @return lenght of line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * find middle point of line.
     *
     * @return middle point of line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * get start point of line.
     *
     * @return start point of line
     */
    public Point start() {
        return this.start;
    }

    /**
     * get end point of line.
     *
     * @return end point of line
     */
    public Point end() {
        return this.end;
    }

    /**
     * get tilt of line.
     *
     * @return tilt of line
     */
    public double tilt() {
        return this.tilt;
    }

    /**
     * get parameter line.
     *
     * @return parameter of line
     */
    public double b() {
        return this.b;
    }

    /**
     * check if their is an intersection point.
     *
     * @param other line to check
     * @return true if their is, else false
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * get the intersection point.
     *
     * @param other line to check
     * @return point of the intersection, if their isnt return null
     */
    public Point intersectionWith(Line other) {
        if (this.tilt == other.tilt()) {
            return null;
        }
        double pX, pY;
        if (this.tilt + other.tilt() == Double.POSITIVE_INFINITY) {
            Line inf, reg;
            if (this.tilt > other.tilt()) {
                inf = this;
                reg = other;
            } else {
                reg = this;
                inf = other;
            }
            pX = inf.b();
            pY = (reg.tilt * pX) + reg.b;
        } else {
            pX = (this.b - other.b()) / (other.tilt() - this.tilt);
            pY = (this.tilt * pX) + this.b;
        }
        Point intesection = new Point(pX, pY);
        if (checkPointIn(this, intesection) && checkPointIn(other, intesection)) {
            return intesection;
        }
        return null;
    }

    /**
     * check if point in line.
     *
     * @param l line
     * @param p point
     * @return true if inside, else false
     */
    private boolean checkPointIn(Line l, Point p) {
        double t;
        if (l.end().getX() - l.start().getX() != 0) {
            t = (p.getX() - l.start().getX()) / (l.end().getX() - l.start().getX());
        } else {
            t = (p.getY() - l.start().getY()) / (l.end().getY() - l.start().getY());
        }
        if (-0.001 <= t && t <= 1.001) {
            return true;
        }
        return false;
    }

    /**
     * check if the lines are equals.
     *
     * @param other line to check
     * @return true if equals, otherwise false
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * get closest intersection point to rec (if their is) to start point.
     *
     * @param rect rectangle to check with
     * @return closest point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionP = rect.intersectionPoints(this);
        return closestPoint(intersectionP);
    }

    /**
     * get closest point from a list.
     *
     * @param points list of points
     * @return closest point
     */
    public Point closestPoint(List<Point> points) {
        Point minPoint = null;
        double distance = Double.POSITIVE_INFINITY;
        for (Point point : points) {
            if (distance > this.start.distance(point)) {
                distance = this.start.distance(point);
                minPoint = point;
            }
        }
        return minPoint;
    }

    /**
     * main, with a little test.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Line l1 = new Line(0, 0, 5, 5);
        Line l2 = new Line(2, 0, 5, 0);
        Line l3 = new Line(0, 2, 0, 12);
        Point p1 = l1.intersectionWith(l2);
        Point p2 = l1.intersectionWith(l3);


    }
}