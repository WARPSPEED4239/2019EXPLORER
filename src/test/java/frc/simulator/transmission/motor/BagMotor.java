package frc.simulator.transmission.motor;

public class BagMotor implements DCMotor {

    @Override
    public double getNominalVoltage() {
        return 12.0;
    }

    @Override
    public double getFreeSpeed() {
        return 13180.0;
    }

    @Override
    public double getFreeCurrent() {
        return 1.8;
    }

    @Override
    public double getStallCurrent() {
        return 53.0;
    }

    @Override
    public double getStallTorque() {
        return 0.43;
    }
    
}