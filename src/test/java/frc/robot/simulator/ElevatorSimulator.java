package frc.robot.simulator;

import frc.robot.simulator.transmission.Transmission;

public class ElevatorSimulator {

    private double mPosition = 0.0;
    private double mVelocity = 0.0;
    private double mAcceleartion = 0.0;

    private Transmission mTransmission;
    private double mMass;
    private double mPulleyRadius;

    public ElevatorSimulator(Transmission transmission, double mass, double pulleyRadius) {
        mTransmission = transmission;
        mMass = mass;
        mPulleyRadius = pulleyRadius;
    }

    public void simultateTime(double voltage, double time) {
        final double kSimTime = 0.0001;
        double currentTime = 0.0;
        while (currentTime < time) {
            double current = mTransmission.getCurrent(voltage, mVelocity / mPulleyRadius);
            double torque = mTransmission.getTorque(current);
            
            mAcceleartion = torque / mPulleyRadius / mMass;
            mPosition += mVelocity * kSimTime;
            mVelocity += mAcceleartion * kSimTime;

            currentTime += kSimTime;
        }
    }

    public double getPosition() {
        return mPosition;
    }

    public double getVelocity() {
        return mVelocity;
    }

    public double getAcceleration() {
        return mAcceleartion;
    }

}