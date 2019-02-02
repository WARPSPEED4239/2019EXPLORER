package frc.robot.tools.vision;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.opencv.core.Point;

 public class PointFinderTests {

    public PointFinderTests() {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
    }

    private void checkPoints(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {
        double[] x = new double[] {
            topLeft.x,
            topRight.x,
            bottomLeft.x,
            bottomRight.x
        };
        double[] y = new double[] {
            topLeft.y,
            topRight.y,
            bottomLeft.y,
            bottomRight.y
        };

        PointFinder pf = new PointFinder(x, y);

        assertEquals(topLeft, pf.getTopLeft());
        assertEquals(topRight, pf.getTopRight());
        assertEquals(bottomLeft, pf.getBottomLeft());
        assertEquals(bottomRight, pf.getBottomRight());
    }

    @Test
    public void testThreeFeetBackOneFootLeftZeroAngle() {
        Point topLeft = new Point(241.0, 102.0);
        Point topRight = new Point(255.0, 104.0);
        Point bottomLeft = new Point(233.0, 141.0);
        Point bottomRight = new Point(248.0, 143.0);

        checkPoints(topLeft, topRight, bottomLeft, bottomRight);
    }

}