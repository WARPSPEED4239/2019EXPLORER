package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
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
    if (Robot.m_wrist.getBottomLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() <= 0.0) {
      Robot.m_wrist.setPercentOutput(0.0);
    } else if (Robot.m_elevator.getTopLimitSwitch() && Robot.m_wrist.getVelocityInDegreesPerSecond() >= 0.0) {
      Robot.m_elevator.setPercentOutput(0.0);
    } else {
      Robot.m_elevator.setPosition(mPositionInDegrees); // If this doesn't work, call only this
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
