package frc.simulator.transmission.motor;

public interface DCMotor {
    public double getNominalVoltage();
    public double getFreeSpeed();
    public double getFreeCurrent();
    public double getStallCurrent();
    public double getStallTorque();
}