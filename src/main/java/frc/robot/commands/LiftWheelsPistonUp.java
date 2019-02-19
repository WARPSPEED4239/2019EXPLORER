package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelsPistonUp extends Command {
  public LiftWheelsPistonUp() {
    requires(Robot.m_liftWheelsPiston);
  }

  @Override
  protected void initialize() {
    Robot.m_liftWheelsPiston.extend();
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
