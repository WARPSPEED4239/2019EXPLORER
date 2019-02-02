package frc.robot.tools.vision;

import org.opencv.core.Mat;

import frc.robot.tools.UnitConversion;

public class VisionData {

    private final Mat mTranslationVector;
    private final Mat mEulerAngles;

    public VisionData(Mat translationVector, Mat eulerAngles) {
        mTranslationVector = translationVector;
        mEulerAngles = eulerAngles;
    }

    public double getX() {
        double xInInches = mTranslationVector.get(0, 0)[0];
        return UnitConversion.convertInchesToFeet(xInInches);
    }

    public double getY() {
        double yInInches = mTranslationVector.get(1, 0)[0];
        return UnitConversion.convertInchesToFeet(yInInches);
    }

    public double getZ() {
        double zInInches = mTranslationVector.get(2, 0)[0];
        return UnitConversion.convertInchesToFeet(zInInches);
    }

    public double getRoll() {
        return mEulerAngles.get(2, 0)[0];
    }

    public double getPitch() {
        return mEulerAngles.get(0, 0)[0];
    }

    public double getYaw() {
        return mEulerAngles.get(1, 0)[0];
    }

}