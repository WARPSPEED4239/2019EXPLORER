package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RampPistonExtend extends Command {
  public RampPistonExtend() {
    requires(Robot.m_ramps);
  }

  @Override
  protected void initialize() {
    Robot.m_rampPiston.pistonExtend();
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
  }
}
