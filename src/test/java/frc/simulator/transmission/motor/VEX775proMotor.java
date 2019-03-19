package frc.simulator.transmission.motor;

public class VEX775proMotor implements DCMotor {

    @Override
    public double getNominalVoltage() {
        return 12.0;
    }

    @Override
    public double getFreeSpeed() {
        return 18730.0;
    }

    @Override
    public double getFreeCurrent() {
        return 0.7;
    }

    @Override
    public double getStallCurrent() {
        return 134.0;
    }

    @Override
    public double getStallTorque() {
        return 0.71;
    }
    
}