package frc.robot.tools;

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

    public VisionProcessor() {
        // Define bottom right corner of left vision target as origin
        mObjectPoints = new MatOfPoint3f(
            new Point3(0.0, 0.0, 0.0), // bottom right
            new Point3(-1.9363, 0.5008, 0.0), // bottom left
            new Point3(-0.5593, 5.8258, 0.0), // top-left
            new Point3(1.377, 5.325, 0.0) // top-right
        );

        mCameraMatrix = Mat.eye(3, 3, CvType.CV_64F);
        mCameraMatrix.put(0, 0, 2.5751292067328632e+02);
        mCameraMatrix.put(0, 2, 1.5971077914723165e+02);
        mCameraMatrix.put(1, 1, 2.5635071715912881e+02);
        mCameraMatrix.put(1, 2, 1.1971433393615548e+02);
        
        mDistortionCoefficients = new MatOfDouble(
            2.9684613693070039e-01,
            -1.4380252254747885e+00,
            -2.2098421479494509e-03,
            -3.3894563533907176e-03,
            2.5344430354806740e+00
        );
    }

    public void update() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);

        NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        double[] cornX = limelightTable.getEntry("tcornx").getDoubleArray(new double[0]);
        double[] cornY = limelightTable.getEntry("tcornx").getDoubleArray(new double[0]);

        if (cornX.length != 4 || cornY.length != 4) {
            System.out.println("[ERROR] Could not find 4 points from image");
            return;
        }

        MatOfPoint2f imagePoints = new MatOfPoint2f(
            new Point(cornX[0], cornY[0]),
            new Point(cornX[1], cornY[1]),
            new Point(cornX[2], cornY[2]),
            new Point(cornX[3], cornY[3])
        );

        Mat rotationVector = new Mat();
        Mat translationVector = new Mat();
        Calib3d.solvePnP(mObjectPoints, imagePoints, mCameraMatrix, mDistortionCoefficients, rotationVector, translationVector);
        
        System.out.println("rotationVector: " + rotationVector);
        System.out.println("translationVector: " + translationVector);
    }

}