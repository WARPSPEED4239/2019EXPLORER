package frc.robot.tools.vision;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VisionProcessorTests {

    private final double kAcceptableTranslationErrorInFeet = 1.0 / 12.0;
    private final double kAcceptableRotationErrorInDegrees = 1.0;

    public VisionProcessorTests() {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
    }

    private void checkPoseEstimate(double knownX, double knownZ, double knownYaw, double[] cornX, double[] cornY) {
        VisionProcessor vp = new VisionProcessor(); 
        vp.update(cornX, cornY);
        
        VisionData vd = vp.getVisionData();

        assertEquals(knownX, vd.getX(), kAcceptableTranslationErrorInFeet);
        assertEquals(knownZ, vd.getZ(), kAcceptableTranslationErrorInFeet);
        assertEquals(knownYaw, vd.getYaw(), kAcceptableRotationErrorInDegrees); 
    }

    @Test
    public void testPoseEstimateOne() {
        final double knownX = 1.0; // 1 foot left
        final double knownZ = 3.0; // 3 feet back
        final double knownYaw = 0.0; // Perpendicular with target

        final double[] cornX = new double[] {241.0, 233.0, 248.0, 255.0};
        final double[] cornY = new double[] {102.0, 141.0, 143.0, 104.0};

        checkPoseEstimate(knownX, knownZ, knownYaw, cornX, cornY);
    }

}