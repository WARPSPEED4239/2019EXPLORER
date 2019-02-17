package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristManualControl extends Command {
  public WristManualControl() {
    requires(Robot.m_wrist);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double output = 0.0;//-Robot.m_oi.getJoystick().getY();
    
    if (Robot.m_wrist.getBottomLimitSwitch() && output > 0.0) {
      output = 0.0;
    }
    else if (Robot.m_wrist.getTopLimitSwitch() && output < 0.0) {
      output = 0.0;
    }

    Robot.m_wrist.setPercentOutput(output);
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
