package frc.robot.subsystems;

import org.junit.Test;

import frc.robot.simulator.ElevatorSimulator;
import frc.robot.simulator.transmission.Transmission;
import frc.robot.simulator.transmission.motor.DCMotor;
import frc.robot.simulator.transmission.motor.NeoMotor;

public class ElevatorTests {

    ElevatorSimulator getSimulator() {
        final DCMotor motor = new NeoMotor();
        final int numberOfMotors = 3;
        final double gearRatio = 12.75;
        final Transmission transmission = new Transmission(motor, numberOfMotors, gearRatio);

        double massInKilograms = 5;
        double pulleyRadiusInMeters = 0.5;
        return new ElevatorSimulator(transmission, massInKilograms, pulleyRadiusInMeters);
    }

    @Test
    public void testDummy() {
    }

}