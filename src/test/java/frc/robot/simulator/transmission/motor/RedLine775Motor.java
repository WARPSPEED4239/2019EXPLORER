package frc.robot.simulator.transmission.motor;;

public class RedLine775Motor implements DCMotor {

    @Override
    public double getNominalVoltage() {
        return 12.0;
    }

    @Override
    public double getFreeSpeed() {
        return 19649.0;
    }

    @Override
    public double getFreeCurrent() {
        return 3.162;
    }

    @Override
    public double getStallCurrent() {
        return 106.981;
    }

    @Override
    public double getStallTorque() {
        return 0.62726;
    }
    
}