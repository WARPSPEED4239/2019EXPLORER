package frc.simulator.transmission;

import frc.simulator.transmission.motor.DCMotor;
import frc.tools.UnitConversion;

public class Transmission {
    private final double kNominalVoltage;
    private final double kFreeSpeed;
    private final double kFreeCurrent;
    private final double kStallCurrent;
    private final double kStallTorque;

    private final double kResistance;
    private final double kT;
    private final double kV;
    
    public Transmission(DCMotor motor, int numberOfMotors, double gearRatio) {
        kNominalVoltage = motor.getNominalVoltage();
        kFreeSpeed = motor.getFreeSpeed() / gearRatio;
        kFreeCurrent = motor.getFreeCurrent() * numberOfMotors;
        kStallCurrent = motor.getStallCurrent() * numberOfMotors;
        kStallTorque = motor.getStallTorque() * gearRatio;

        kResistance = kNominalVoltage / kStallCurrent;
        kT = kStallCurrent / kStallTorque;
        kV = (kNominalVoltage - kResistance * kFreeCurrent) / UnitConversion.convertRevolutionsPerMinuteToRadiansPerSecond(kFreeSpeed);
    }

    public double getTorque(double current) {
        return current / kT;
    }

    public double getCurrent(double voltage, double angularVelocity) {
        double voltageFromVelocity = kV * angularVelocity;
        return (voltage - voltageFromVelocity) / kResistance;
    }

}