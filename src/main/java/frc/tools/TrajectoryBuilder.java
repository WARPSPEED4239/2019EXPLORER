package frc.tools;

import java.util.ArrayList;
import java.util.List;

import grpl.pathfinder.Vec2;
import grpl.pathfinder.coupled.CoupledCausalTrajGen;
import grpl.pathfinder.coupled.CoupledChassis;
import grpl.pathfinder.coupled.CoupledState;
import grpl.pathfinder.coupled.CoupledWheelState;
import grpl.pathfinder.path.ArcParameterizer;
import grpl.pathfinder.path.Curve2d;
import grpl.pathfinder.path.HermiteFactory;
import grpl.pathfinder.path.HermiteQuintic;
import grpl.pathfinder.profile.TrapezoidalProfile;
import grpl.pathfinder.transmission.DcMotor;

public class TrajectoryBuilder {
    private DcMotor mMotor;
    private CoupledChassis mChassis;
    private CoupledCausalTrajGen mGenerator;
    private ArcParameterizer mParameterizer;
    private TrapezoidalProfile mProfile;
    private CoupledState mState;

    private List<HermiteQuintic.Waypoint> mVisited;

    private double mLeftDistance = 0.0;
    private double mRightDistance = 0.0;
    private double mLastTime = 0.0;

    public TrajectoryBuilder() {
        final double numberOfMotors = 3.0;
        final double gearRatio = 12.75;

        final double voltageNominalInVolts = 12.0;
        final double freeSpeedInRadiansPerSecond = UnitConversion.convertRevolutionsPerMinuteToRadiansPerSecond(5676);
        final double freeCurrentInAmps = 1.8;
        final double stallCurrentInAmps = 105; 
        final double stallTorqueInNewtonMeters = 2.6;

        final double wheelRadiusInMeters = UnitConversion.convertFeetToMeters(0.5);
        final double trackRadiusInMeters = UnitConversion.convertFeetToMeters(2.75 / 2);
        final double massInKilograms = UnitConversion.convertPoundsToKilograms(200.0);

        mMotor = new DcMotor(voltageNominalInVolts, freeSpeedInRadiansPerSecond / gearRatio, numberOfMotors * freeCurrentInAmps, numberOfMotors * stallCurrentInAmps, numberOfMotors * stallTorqueInNewtonMeters * gearRatio);
        mChassis = new CoupledChassis(mMotor, mMotor, wheelRadiusInMeters, trackRadiusInMeters, massInKilograms);
        mGenerator = new CoupledCausalTrajGen(mChassis);
        mParameterizer = new ArcParameterizer();
        mProfile = new TrapezoidalProfile();
        mParameterizer.configure(0.01, 0.01);
        mVisited = new ArrayList<>();
        mState = new CoupledState();
    }

    public void updateTarget(HermiteQuintic.Waypoint target) {
        mVisited.add(new HermiteQuintic.Waypoint(mState.config.position, Vec2.polar(1, mState.config.heading), Vec2.cartesian(0, 0)));

        List<HermiteQuintic.Waypoint> waypoints = new ArrayList<>(mVisited);
        waypoints.add(target);

        List<HermiteQuintic> hermites = HermiteFactory.generateQuintic(waypoints);
        List<? extends Curve2d> curves = mParameterizer.parameterize(hermites);
        mGenerator.configure(curves, mProfile);
    }

    public CoupledWheelState[] follow(double time) {
        if (mVisited.size() == 0) {
            return null;
        }

        mState = mGenerator.generate(mState, time);
        CoupledWheelState[] leftRightState = mChassis.split(mState);

        CoupledWheelState left = leftRightState[CoupledCausalTrajGen.LEFT];
        CoupledWheelState right = leftRightState[CoupledCausalTrajGen.RIGHT];

        left.kinematics.distance = mLeftDistance;
        right.kinematics.distance = mRightDistance;

        double dt = time - mLastTime;
        mLeftDistance += (left.kinematics.velocity * dt) + (0.5 * left.kinematics.acceleration * dt * dt);
        mRightDistance += (right.kinematics.velocity * dt) + (0.5 * right.kinematics.acceleration * dt * dt);
        mLastTime = time;

        return leftRightState;
    }

    public boolean isFinished() {
        return mState.finished;
    }
}