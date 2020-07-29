package geometry.visible;

import java.util.LinkedList;
import java.util.List;

import settings.GameStandarts;
import settings.Logger;
import geometry.invisible.Point;
import geometry.invisible.Velocity;

/**
 * my rectangle class.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;
    private int offset;
    private List<Point> points;
    private Logger log;

    /**
     * Constactor.
     *
     * @param upperLeft upper left point
     * @param width     WIDTH of rec
     * @param height    HEIGHT of rec
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.offset = GameStandarts.BALL_RADIUS;
        this.points = new LinkedList<>();
        initialPoints();
    }

    /**
     * set Logger.
     *
     * @param l logger
     */
    public void setLog(Logger l) {
        log = l;
    }

    /**
     * find intersection with all rectangle's lines.
     *
     * @param line line
     * @return list of intersection
     */
    public List<Point> intersectionPoints(Line line) {
        if (!checkInSideRec(line.start())) {
            return intesctionNotInRec(line);
        }
        return intesctionInRec(line);
    }

    /**
     * find intersection when the start of the line isn't in the rec.
     *
     * @param line line
     * @return list of points
     */
    private List<Point> intesctionNotInRec(Line line) {
        List<Point> intersectionP = new LinkedList<>();
        List<Line> rectangleLines = getRectangleLines();
        Velocity currentVelocity = new Velocity(line.end().add(line.start(), -1));
        for (Line l : rectangleLines) {
            Point temp = line.intersectionWith(l);
            Velocity tmp = new Velocity(l.end().add(l.start(), -1));
            if (temp != null && currentVelocity.cross(tmp) < 0) { //check
                intersectionP.add(temp);
            }
        }
        for (Point p : this.points) {
            if (line.end().distance(p) >= offset) {
                continue;
            }
            Velocity v1 = new Velocity(line.end().add(line.start(), -1));
            Velocity v2 = new Velocity(p.add(line.start(), -1));
            double dist = v1.cross(v2) / line.end().distance(line.start());
            if (dist >= offset) {
                continue;
            }
            double x = Math.sqrt(Math.pow(offset, 2) - Math.pow(dist, 2));
            double len = v1.dot(v2) / line.end().distance(line.start());
            Velocity v3 = Velocity.fromAngleAndSpeed(v1.getAngle(), len - x);
            intersectionP.add(v3.applyToPoint(line.start()));
        }
        return intersectionP;
    }

    /**
     * find intersection when the start of the line in the rec.
     *
     * @param line line
     * @return list of points
     */
    private List<Point> intesctionInRec(Line line) {
        List<Point> intersectionP = new LinkedList<>();
        Velocity v = new Velocity(line.start().add(line.end(), -1));
        Point start = v.applyToPoint(line.start());
        Point end = line.start();
        for (int i = 0; i < 4; i++) {
            intersectionP = intesctionNotInRec(new Line(start, end));
            if (!intersectionP.isEmpty()) {
                break;
            }
            Point tmp = start;
            start = v.applyToPoint(start);
            end = tmp;
        }
        return intersectionP;
    }

    /**
     * get all rectangles line (with offsets).
     *
     * @return all rec lines
     */
    public List<Line> getRectangleLines() {
        List<Line> lines = new LinkedList<>();
        Point startL1 = new Point(upperLeft.getX() - offset, upperLeft.getY());
        Point endL1 = new Point(startL1.getX(), startL1.getY() + width);
        Point startL2 = new Point(upperLeft.getX(), upperLeft.getY() + width + offset);
        Point endL2 = new Point(startL2.getX() + height, startL2.getY());
        Point startL3 = new Point(upperLeft.getX() + height + offset, upperLeft.getY() + width);
        Point endL3 = new Point(startL3.getX(), startL3.getY() - width);
        Point startL4 = new Point(upperLeft.getX() + height, upperLeft.getY() - offset);
        Point endL4 = new Point(upperLeft.getX(), startL4.getY());
        lines.add(new Line(endL1, startL1));
        lines.add(new Line(endL2, startL2));
        lines.add(new Line(endL3, startL3));
        lines.add(new Line(endL4, startL4));
        return lines;
    }

    /**
     * get for WIDTH.
     *
     * @return this WIDTH
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get for HEIGHT.
     *
     * @return this HEIGHT
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get fot point upper left.
     *
     * @return this upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * get all corners of rectangle.
     *
     * @return list of point
     */
    public List<Point> getCornerPoints() {
        return this.points;
    }

    /**
     * set for upper left.
     *
     * @param upperL new upper left
     */
    public void setUpperLeft(Point upperL) {
        this.upperLeft = upperL;
        initialPoints();
    }

    /**
     * get offset.
     *
     * @return this offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * initial corner point of rec.
     */
    public void initialPoints() {
        points.clear();
        points.add(upperLeft);
        points.add(new Point(upperLeft.getX() + height, upperLeft.getY()));
        points.add(new Point(upperLeft.getX(), upperLeft.getY() + width));
        points.add(new Point(upperLeft.getX() + height, upperLeft.getY() + width));
    }

    /**
     * minimum distance from corner.
     *
     * @param p point to find the minimum diatnce from corner
     * @return minimum distance
     */
    public double distanceFromCorners(Point p) {
        double mindist = Double.POSITIVE_INFINITY;
        initialPoints();
        for (Point point : points) {
            if (mindist > point.distance(p)) {
                mindist = point.distance(p);
            }
        }
        return mindist;
    }

    /**
     * check if point inside rec (with offset).
     *
     * @param point point to check
     * @return if inside or not
     */
    public boolean checkInSideRec(Point point) {
        Point upperLeft1 = new Point(upperLeft.getX() - offset, upperLeft.getY());
        Point upperLeft2 = new Point(upperLeft.getX(), upperLeft.getY() - offset);

        if (new Rectangle(upperLeft1, width, height + 2 * offset).checkInsideRecWithoutOff(point)) {
            return true;
        }
        if (new Rectangle(upperLeft2, width + 2 * offset, height).checkInsideRecWithoutOff(point)) {
            return true;
        }
        if (distanceFromCorners(point) < offset) {
            return true;
        }
        return false;
    }

    /**
     * check if point inside rec (without offset).
     *
     * @param point point to check
     * @return if inside or not
     */
    public boolean checkInsideRecWithoutOff(Point point) {
        if (point.getX() >= upperLeft.getX() && point.getX() <= upperLeft.getX() + height) {
            if (point.getY() >= upperLeft.getY() && point.getY() <= upperLeft.getY() + width) {
                return true;
            }
        }
        return false;
    }
}