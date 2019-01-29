package frc.subsystems;

import org.junit.Test;

import frc.simulator.ElevatorSimulator;
import frc.simulator.transmission.Transmission;
import frc.simulator.transmission.motor.DCMotor;
import frc.simulator.transmission.motor.NeoMotor;

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
        final ElevatorSimulator simulator = getSimulator();
        final double loopRate = 0.005; // 200 Hz
        final double runtime = 5.0;

        System.out.println("Time, Position, Velocity, Acceleration");

        double currentTime = 0.0;
        while (currentTime < runtime) {
            System.out.println(currentTime + ", " + simulator.getPosition() + ", " + simulator.getVelocity() + ", " + simulator.getAcceleration());
            simulator.simultateTime(12.0, loopRate);
            currentTime += loopRate;
        }
    }

}