package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristStop extends Command {
  public WristStop() {
    requires(Robot.m_wrist);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_wrist.wristMotorStop();
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
    end();
  }
}
