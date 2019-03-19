package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class WristSetPercentOutputWithJoystick extends Command {

  private double mOutput;

  public WristSetPercentOutputWithJoystick() {
    requires(Robot.m_wrist);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Constants.kCodeTestingState) {
      mOutput = -Robot.m_oi.getJoystick().getY();

      if (Robot.m_wrist.getBottomLimitSwitch() && mOutput > 0.0) {
        mOutput = 0.0;
      }
      else if (Robot.m_wrist.getTopLimitSwitch() && mOutput < 0.0) {
        mOutput = 0.0;
      }
    }
    else {
      mOutput = 0.0;
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
