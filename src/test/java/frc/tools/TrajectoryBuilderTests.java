package frc.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import grpl.pathfinder.Vec2;
import grpl.pathfinder.coupled.CoupledCausalTrajGen;
import grpl.pathfinder.coupled.CoupledState;
import grpl.pathfinder.coupled.CoupledWheelState;
import grpl.pathfinder.path.HermiteQuintic;

public class TrajectoryBuilderTests {
    public static final double METERS_PER_FEET = 0.3048;
    public static final double FEET_PER_METER = 3.28084;

    @org.junit.Test
    public void test() {
        TrajectoryBuilder builder = new TrajectoryBuilder();
        builder.updateTarget(new HermiteQuintic.Waypoint(Vec2.cartesian(4, 4), Vec2.cartesian(0, 5), Vec2.cartesian(0, 0)));

        double t = 0;
        int updateCount = 0;
        while (!builder.isFinished() && t < 5) {
            if (t >= 1 && updateCount < 1) {
                builder.updateTarget(new HermiteQuintic.Waypoint(Vec2.cartesian(8, 4), Vec2.cartesian(0, -5), Vec2.cartesian(0, 0)));
                updateCount++;
            }
            if (t >= 2 && updateCount < 2) {
                builder.updateTarget(new HermiteQuintic.Waypoint(Vec2.cartesian(6, 5), Vec2.cartesian(1, 1), Vec2.cartesian(0, 0)));
                updateCount++;
            }

            CoupledWheelState[] state = builder.follow(t);
            //System.out.println("left: " + toString(state[CoupledCausalTrajGen.LEFT]));
            //System.out.println("right: " + toString(state[CoupledCausalTrajGen.RIGHT]));

            t += 0.01;
        }
    }

    private String toString(CoupledWheelState state) {
        return "pos = " + UnitConversion.convertMetersToFeet(state.kinematics.distance) + 
               ", vel = " + UnitConversion.convertMetersToFeet(state.kinematics.velocity) + 
               ", acc = " + UnitConversion.convertMetersToFeet(state.kinematics.acceleration) +
               ", voltage = " + state.voltage;
    }
}