package frc.robot.tools.vision;

import org.opencv.core.Point;

class PointFinder {

    private final Point mBottomLeft;
    private final Point mBottomRight;
    private final Point mTopLeft;
    private final Point mTopRight;
    
    public PointFinder(double[] x, double[] y) {
        mBottomLeft = new Point();
        mBottomRight = new Point();
        mTopLeft = new Point();
        mTopRight = new Point();
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