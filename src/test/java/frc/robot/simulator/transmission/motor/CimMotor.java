package frc.robot.simulator.transmission.motor;

public class CimMotor implements DCMotor {

    @Override
    public double getNominalVoltage() {
        return 12.0;
    }

    @Override
    public double getFreeSpeed() {
        return 5330.0;
    }

    @Override
    public double getFreeCurrent() {
        return 2.7;
    }

    @Override
    public double getStallCurrent() {
        return 131.0;
    }

    @Override
    public double getStallTorque() {
        return 2.41;
    }
    
}