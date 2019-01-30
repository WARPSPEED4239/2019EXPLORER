package frc.simulator.transmission.motor;

public class MiniCimMotor implements DCMotor {

    @Override
    public double getNominalVoltage() {
        return 12.0;
    }

    @Override
    public double getFreeSpeed() {
        return 5840.0;
    }

    @Override
    public double getFreeCurrent() {
        return 3.0;
    }

    @Override
    public double getStallCurrent() {
        return 89.0;
    }

    @Override
    public double getStallTorque() {
        return 1.41;
    }
    
}