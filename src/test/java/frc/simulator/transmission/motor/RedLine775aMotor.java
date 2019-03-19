package frc.simulator.transmission.motor;

public class RedLine775aMotor implements DCMotor {

    @Override
    public double getNominalVoltage() {
        return 12.0;
    }

    @Override
    public double getFreeSpeed() {
        return 21020.0;
    }

    @Override
    public double getFreeCurrent() {
        return 3.81;
    }

    @Override
    public double getStallCurrent() {
        return 130.12;
    }

    @Override
    public double getStallTorque() {
        return 0.7;
    }
    
}