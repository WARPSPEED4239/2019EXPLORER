package frc.tools;

import java.util.ArrayList;
import java.util.List;

import grpl.pathfinder.Vec2;
import grpl.pathfinder.coupled.CoupledCausalTrajGen;
import grpl.pathfinder.coupled.CoupledChassis;
import grpl.pathfinder.coupled.CoupledState;
import grpl.pathfinder.path.ArcParameterizer;
import grpl.pathfinder.path.Curve2d;
import grpl.pathfinder.path.HermiteFactory;
import grpl.pathfinder.path.HermiteQuintic;
import grpl.pathfinder.profile.TrapezoidalProfile;
import grpl.pathfinder.transmission.DcMotor;

public class TrajectoryBuilder {

    public static void initialize() {
        DcMotor motor = new DcMotor(12.0, 5330 * 2.0 * Math.PI / 60.0 / 12.75, 2 * 2.7, 2 * 131.0, 2 * 2.41 * 12.75);
        CoupledChassis chassis = new CoupledChassis(motor, motor, 0.0762, 0.5, 25.0);
        CoupledCausalTrajGen gen = new CoupledCausalTrajGen(chassis);
    
        TrapezoidalProfile profile = new TrapezoidalProfile();
    
        List<HermiteQuintic.Waypoint> waypoints = new ArrayList<>();
        waypoints.add(new HermiteQuintic.Waypoint(Vec2.cartesian(0, 0), Vec2.cartesian(5, 0), Vec2.cartesian(0, 0)));
        waypoints.add(new HermiteQuintic.Waypoint(Vec2.cartesian(4, 4), Vec2.cartesian(0, 5), Vec2.cartesian(0, 0)));
    
        List<HermiteQuintic> hermites = HermiteFactory.generateQuintic(waypoints);
        ArcParameterizer param = new ArcParameterizer();
        param.configure(0.01, 0.01);
        List<? extends Curve2d> curves = param.parameterize(hermites);
    
        gen.configure(curves, profile);
    
        CoupledState state = new CoupledState();
    
        for (double t = 0; !state.finished && t < 5; t += 0.01) {
            state = gen.generate(state, t);
            echo(state);
        }
    
        motor.close();
        chassis.close();
        gen.close();
        param.close();
        profile.close();
    }
    
    private static void echo(CoupledState state) {
        Object obj[] = new Object[] {
                state.time, state.config.position.x(), state.config.position.y(),
                state.config.heading, state.kinematics.distance, state.kinematics.velocity, state.kinematics.acceleration,
                state.curvature, 0, 0, 0,
        };
        String output = state.time + ", " + state.config.position.x() + ", " + state.config.position.y();
        System.out.println(output);
    }

}