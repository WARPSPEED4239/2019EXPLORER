package frc.robot.tools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point;
import org.opencv.core.Point3;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionProcessor {

    private MatOfPoint3f mObjectPoints;
    private Mat mCameraMatrix;
    private MatOfDouble mDistortionCoefficients;

    private PoseEstimate mPoseEstimate;

    private NetworkTable limelightTable;

    public VisionProcessor() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

        // Define bottom right corner of left vision target as origin
        mObjectPoints = new MatOfPoint3f(new Point3(0.0, 0.0, 0.0), // bottom right
                new Point3(-1.9363, 0.5008, 0.0), // bottom left
                new Point3(-0.5593, 5.8258, 0.0), // top-left
                new Point3(1.377, 5.325, 0.0) // top-right
        );

        mCameraMatrix = Mat.eye(3, 3, CvType.CV_64F);
        mCameraMatrix.put(0, 0, 2.5751292067328632e+02);
        mCameraMatrix.put(0, 2, 1.5971077914723165e+02);
        mCameraMatrix.put(1, 1, 2.5635071715912881e+02);
        mCameraMatrix.put(1, 2, 1.1971433393615548e+02);

        mDistortionCoefficients = new MatOfDouble(2.9684613693070039e-01, -1.4380252254747885e+00,
                -2.2098421479494509e-03, -3.3894563533907176e-03, 2.5344430354806740e+00);

        limelightTable.getEntry("pipeline").setNumber(0);
        limelightTable.getEntry("camMode").setNumber(0);
        limelightTable.getEntry("ledMode").setNumber(3);
    }

    public synchronized PoseEstimate getPose() {
        return mPoseEstimate;
    }

    public void update() {
        double[] cornX = limelightTable.getEntry("tcornx").getDoubleArray(new double[0]);
        double[] cornY = limelightTable.getEntry("tcorny").getDoubleArray(new double[0]);

        if (cornX.length != 4 || cornY.length != 4) {
            System.out.println("[ERROR] Could not find 4 points from image");
            return;
        }

        PointFinder pointFinder = new PointFinder(cornX, cornY);

        MatOfPoint2f imagePoints = new MatOfPoint2f(pointFinder.getBottomRight(), pointFinder.getBottomLeft(),
                pointFinder.getTopLeft(), pointFinder.getTopRight());

        Mat rotationVector = new Mat();
        Mat translationVector = new Mat();
        Calib3d.solvePnP(mObjectPoints, imagePoints, mCameraMatrix, mDistortionCoefficients, rotationVector,
                translationVector);

        System.out.println("rotationVector: " + rotationVector.dump());
        System.out.println("translationVector: " + translationVector.dump());

        synchronized(this) {
            mPoseEstimate.x = translationVector.get(0, 0)[0] / 12.0;
            mPoseEstimate.y = translationVector.get(2, 0)[0] / 12.0;
            mPoseEstimate.theta = rotationVector.get(1, 0)[0] * 180.0 / Math.PI;
        }
    }

    class PointFinder {
        private Point mTopLeft;
        private Point mTopRight;
        private Point mBottomLeft;
        private Point mBottomRight;

        public PointFinder(double[] x, double[] y) {
            double[] xSorted = new double[4];
            Map<Double, Double> xyMap = new HashMap<>();
            for (int i = 0; i < 4; i++) {
                xyMap.put(x[i], 240.0 - y[i]);
                xSorted[i] = x[i];
            }
            Arrays.sort(xSorted);

            double topLeftX, bottomLeftX;
            if (xyMap.get(xSorted[0]) > xyMap.get(xSorted[1])) {
                topLeftX = xSorted[0];
                bottomLeftX = xSorted[1];
            } else {
                topLeftX = xSorted[1];
                bottomLeftX = xSorted[0];
            }

            double topRightX, bottomRightX;
            if (xyMap.get(xSorted[2]) > xyMap.get(xSorted[3])) {
                topRightX = xSorted[2];
                bottomRightX = xSorted[3];
            } else {
                topRightX = xSorted[3];
                bottomRightX = xSorted[2];
            }

            mTopLeft = new Point(topLeftX,  xyMap.get(topLeftX));
            mTopRight = new Point(topRightX,  xyMap.get(topRightX));
            mBottomLeft = new Point(bottomLeftX,  xyMap.get(bottomLeftX));
            mBottomRight = new Point(bottomRightX, xyMap.get(bottomRightX));

            /*System.out.println("topLeft: " + mTopLeft);
            System.out.println("mTopRight: " + mTopRight);
            System.out.println("mBottomLeft: " + mBottomLeft);
            System.out.println("mBottomRight: " + mBottomRight);

            System.out.println("input: {" + x[0] + ", " + y[0] + "}" + 
                                    ", {" + x[1] + ", " + y[1] + "}" + 
                                    ", {" + x[2] + ", " + y[2] + "}" + 
                                    ", {" + x[3] + ", " + y[3] + "}"
            );          

            System.out.println("topLeft: " + mTopLeft);
            System.out.println("topRight: " + mTopRight);
            System.out.println("bottomLeft: " + mBottomLeft);
            System.out.println("bottomRight: " + mBottomRight);*/
        }

        public Point getTopLeft() {
            return mTopLeft;
        }

        public Point getTopRight() {
            return mTopRight;
        }

        public Point getBottomLeft() {
            return mBottomLeft;
        }

        public Point getBottomRight() {
            return mBottomRight;
        }

    }

    class PoseEstimate {
        public double x;
        public double y;
        public double theta;

        public String toString() {
            return "x = " + x + ", y = " + y + ", theta = " + theta;
        }
    }
    
}