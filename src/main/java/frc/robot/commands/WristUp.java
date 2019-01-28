package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristUp extends Command {
  public WristUp() {
    requires(Robot.m_wrist);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_wrist.wristMotorUp();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_wrist.getWristLimitSwitch();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
