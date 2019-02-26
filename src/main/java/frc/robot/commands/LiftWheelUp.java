package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelUp extends Command {
  public LiftWheelUp() {
    requires(Robot.m_liftWheel);
  }

  @Override
  protected void initialize() {
    Robot.m_liftWheel.up();
  }

  @Override
  protected void execute() {
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
