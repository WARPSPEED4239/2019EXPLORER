package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class WristSetPostitionWithJoystick extends Command {
  public WristSetPostitionWithJoystick() {
    requires(Robot.m_wrist);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_wrist.getBottomLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() <= 0.0) {
      Robot.m_wrist.setPercentOutput(0.0);
    } else if (Robot.m_wrist.getTopLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() >= 0.0) {
      Robot.m_wrist.setPercentOutput(0.0);
    } else {
      double targetPosition = 90 * -Robot.m_oi.getJoystick().getY();
      Robot.m_wrist.setPositionInDegrees(targetPosition); // If this doesn't work, call only this
      SmartDashboard.putNumberArray("Target Position", new double [] {targetPosition, Robot.m_wrist.getPositionInDegrees()});
    }
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
