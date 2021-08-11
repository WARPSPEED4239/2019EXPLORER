package frc.robot.tools.vision;

import org.opencv.core.Point;

class PointFinder {

    private final Point mBottomLeft;
    private final Point mBottomRight;
    private final Point mTopLeft;
    private final Point mTopRight;

    private double getDistance(Point p1, Point p2) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public PointFinder(double[] x, double[] y) {
        Point[] groupOne = new Point[2];
        groupOne[0] = new Point(x[0], y[0]);

        int closestToPointZeroIndex = 1;
        double closestToPointZeroDistance = Double.POSITIVE_INFINITY;
        for (int i = 1; i < 4; i++) {
            Point p = new Point(x[i], y[i]);
            double distance = getDistance(groupOne[0], p);
            if (distance < closestToPointZeroDistance) {
                closestToPointZeroIndex = i;
                closestToPointZeroDistance = distance;
                groupOne[1] = p;
            }
        }

        Point[] groupTwo = new Point[2];
        int groupTwoIndex = 0;
        for (int i = 1; i < 4; i++) {
            if (i != closestToPointZeroIndex) {
                groupTwo[groupTwoIndex] = new Point(x[i], y[i]);
                groupTwoIndex++;
            }
        }

        double groupOneY = groupOne[0].y + groupOne[1].y;
        double groupTwoY = groupTwo[0].y + groupTwo[1].y;

        Point[] top;
        Point[] bottom;
        if (groupOneY > groupTwoY) {
            top = groupTwo;
            bottom = groupOne;
        }
        else {
            top = groupOne;
            bottom = groupTwo;
        }

        if (top[0].x > top[1].x) {
            mTopLeft = top[1];
            mTopRight = top[0];
        }
        else {
            mTopLeft = top[0];
            mTopRight = top[1];
        }

        if (bottom[0].x > bottom[1].x) {
            mBottomLeft = bottom[1];
            mBottomRight = bottom[0];
        }
        else {
            mBottomLeft = bottom[0];
            mBottomRight = bottom[1];
        }
    }

    public Point getBottomLeft() {
        return mBottomLeft;
    }

    public Point getBottomRight() {
        return mBottomRight;
    }

    public Point getTopLeft() {
        return mTopLeft;
    }

    public Point getTopRight() {
        return mTopRight;
    }

}