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

        double groupOneX = groupOne[0].x + groupOne[1].x;
        double groupTwoX = groupTwo[0].x + groupTwo[1].x;

        Point[] left;
        Point[] right;
        if (groupOneX > groupTwoX) {
            left = groupTwo;
            right = groupOne;
        }
        else {
            left = groupOne;
            right = groupTwo;
        }

        if (left[0].y < left[1].y) {
            mTopLeft = left[0];
            mBottomLeft = left[1];
        }
        else {
            mTopLeft = left[1];
            mBottomLeft = left[0];
        }

        if (right[0].y < right[1].y) {
            mTopRight = right[0];
            mBottomRight = right[1];
        }
        else {
            mTopRight = right[1];
            mBottomRight = right[0];
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