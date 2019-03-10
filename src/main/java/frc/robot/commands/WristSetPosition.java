package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class WristSetPosition extends Command {

  private final double mPositionInDegrees;

  public WristSetPosition(double positionInDegrees) {
    requires(Robot.m_wrist);

    mPositionInDegrees = positionInDegrees;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double targetPosition = mPositionInDegrees;
    if (targetPosition > 85 && !Robot.m_elevator.getBottomLimitSwitch()) {
      targetPosition = 85;
    }

    if (Robot.m_wrist.getBottomLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() < -Constants.kEpsilson) {
      Robot.m_wrist.setPercentOutput(0.0);
      Robot.m_wrist.setEncoderValueInDegrees(0.0);
    } else if (Robot.m_wrist.getTopLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() > Constants.kEpsilson) {
      Robot.m_wrist.setPercentOutput(0.0);
      Robot.m_wrist.setEncoderValueInDegrees(146.3378906);
    } else {
      double wristGravityComponnent = Constants.kWristGravityComponentMultiplier * Math.cos(Math.toRadians(Robot.m_wrist.getPositionInDegrees()));
      double wristAcceleration = Constants.kWristAccelerationMultiplier * Robot.m_wrist.getActiveTrajectoryAccelerationInDegreesPerSecondSquared();
      double wristElevatorAcceleration = Constants.kWristElevatorAccelerationMultiplier * Robot.m_elevator.getActiveTrajectoryAccelerationInInchesPerSecondSquared() * Math.cos(Math.toRadians(Robot.m_wrist.getPositionInDegrees()));
      double wristDrivetrainAcceleration = Constants.kWristDrivetrainAccelerationMultiplier * Robot.m_drivetrain.getIMUAccelerationYInMetersPerSecondSquared() /*TODO Right Units?*/ * Math.sin(Math.toRadians(Robot.m_wrist.getPositionInDegrees()));

      double arbFeedForward = wristGravityComponnent + wristAcceleration + wristElevatorAcceleration + wristDrivetrainAcceleration;
      
      Robot.m_wrist.setPositionInDegrees(targetPosition, arbFeedForward);
    }
    SmartDashboard.putNumberArray("Wrist Target Position", new double [] {Robot.m_wrist.getActiveTrajectoryPositionInDegrees(), Robot.m_wrist.getPositionInDegrees()});
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
