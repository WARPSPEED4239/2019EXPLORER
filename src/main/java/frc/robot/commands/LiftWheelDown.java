package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelDown extends Command {
  public LiftWheelDown() {
    requires(Robot.m_liftWheel);
  }

  @Override
  protected void initialize() {
    Robot.m_liftWheel.liftWheelPistonDown();
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
