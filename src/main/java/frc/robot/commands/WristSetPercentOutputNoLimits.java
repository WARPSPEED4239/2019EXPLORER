package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristSetPercentOutputNoLimits extends Command {

  private double mOutput;
  public WristSetPercentOutputNoLimits(double output) {
    requires(Robot.m_wrist);

    mOutput = output;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_wrist.getBottomLimitSwitch() && mOutput > 0.0) {
      Robot.m_wrist.setEncoderValueInDegrees(0.0);
    }
    else if (Robot.m_wrist.getTopLimitSwitch() && mOutput < 0.0) {
      Robot.m_wrist.setEncoderValueInDegrees(146.3378906);
    }
    Robot.m_wrist.setPercentOutput(mOutput);
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