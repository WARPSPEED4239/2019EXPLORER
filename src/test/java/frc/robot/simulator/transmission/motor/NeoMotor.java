package frc.robot.simulator.transmission.motor;;

public class NeoMotor implements DCMotor {

    @Override
    public double getNominalVoltage() {
        return 12.0;
    }

    @Override
    public double getFreeSpeed() {
        return 5676.0;
    }

    @Override
    public double getFreeCurrent() {
        return 1.8;
    }

    @Override
    public double getStallCurrent() {
        return 105.0;
    }

    @Override
    public double getStallTorque() {
        return 2.6;
    }
    
}