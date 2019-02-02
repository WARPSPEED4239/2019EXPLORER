package frc.robot.tools.vision;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point3;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionProcessor {

    private final NetworkTable mLimelightTable;

    private final MatOfPoint3f mObjectPoints;
    private final Mat mCameraMatrix;
    private final MatOfDouble mDistortionCoefficients;

    private VisionData mVisionData;

    public VisionProcessor() {
        mLimelightTable = NetworkTableInstance.getDefault().getTable("limelight");

        // Define bottom right corner of left vision target as origin
        mObjectPoints = new MatOfPoint3f(
            new Point3(0.0, 0.0, 0.0),        // bottom right
            new Point3(-1.9363, 0.5008, 0.0), // bottom left
            new Point3(-0.5593, 5.8258, 0.0), // top-left
            new Point3(1.377, 5.325, 0.0)     // top-right
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
        
        mLimelightTable.getEntry("pipeline").setNumber(0);
        mLimelightTable.getEntry("camMode").setNumber(0);
        mLimelightTable.getEntry("ledMode").setNumber(3);
    }

    public void update() {
        double[] cornX = mLimelightTable.getEntry("tcornx").getDoubleArray(new double[0]);
        double[] cornY = mLimelightTable.getEntry("tcorny").getDoubleArray(new double[0]);

        if (cornX.length != 4 || cornY.length != 4) {
            System.out.println("[ERROR] Could not find 4 points from image");
            return;
        }

        PointFinder pointFinder = new PointFinder(cornX, cornY);

        MatOfPoint2f imagePoints = new MatOfPoint2f(
            pointFinder.getBottomRight(), 
            pointFinder.getBottomLeft(),
            pointFinder.getTopLeft(), 
            pointFinder.getTopRight()
        );

        Mat rotationVector = new Mat();
        Mat translationVector = new Mat();
        Calib3d.solvePnP(mObjectPoints, imagePoints, mCameraMatrix, mDistortionCoefficients, rotationVector,
                translationVector);

        Mat rotationMatrix = new Mat();
        Calib3d.Rodrigues(rotationVector, rotationMatrix);

        Mat projectionMatrix = new Mat(3, 4, CvType.CV_64F);
        projectionMatrix.put(0, 0,
            rotationMatrix.get(0, 0)[0], rotationMatrix.get(0, 1)[0], rotationMatrix.get(0, 2)[0], 0,
            rotationMatrix.get(1, 0)[0], rotationMatrix.get(1, 1)[0], rotationMatrix.get(1, 2)[0], 0,
            rotationMatrix.get(2, 0)[0], rotationMatrix.get(2, 1)[0], rotationMatrix.get(2, 2)[0], 0
        );

        Mat cameraMatrix = new Mat();
        Mat rotMatrix = new Mat();
        Mat transVect = new Mat();
        Mat rotMatrixX = new Mat();
        Mat rotMatrixY = new Mat();
        Mat rotMatrixZ = new Mat(); 
        Mat eulerAngles = new Mat();
        Calib3d.decomposeProjectionMatrix(projectionMatrix, cameraMatrix, rotMatrix, transVect, rotMatrixX, rotMatrixY, rotMatrixZ, eulerAngles);

        synchronized(this) {
            mVisionData = new VisionData(translationVector, eulerAngles);
        }
    }

    public synchronized VisionData getVisionData() {
        return mVisionData;
    }
}