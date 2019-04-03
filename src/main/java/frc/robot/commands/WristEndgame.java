package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class WristEndgame extends Command {

  private final double mPositionInDegrees;

  public WristEndgame(double positionInDegrees) {
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

    if (Robot.m_wrist.getBottomLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() < -Constants.kEpsilon) {
      Robot.m_wrist.setEncoderValueInDegrees(0.0);
      Robot.m_wrist.setPercentOutput(0.0);
      Robot.m_wrist.lockDeploy();
      Robot.m_wrist.setLocked(true);
    } else if (Robot.m_wrist.getTopLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() > Constants.kEpsilon) {
      Robot.m_wrist.setPercentOutput(0.0);
      Robot.m_wrist.setEncoderValueInDegrees(146.3378906);
    } else {
      Robot.m_wrist.setPositionInDegrees(targetPosition, 0);
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
