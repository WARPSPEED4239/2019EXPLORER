package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelsPistonDown extends Command {
  public LiftWheelsPistonDown() {
    requires(Robot.m_liftWheelsPiston);
  }

  @Override
  protected void initialize() {
    Robot.m_liftWheelsPiston.retract();
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
