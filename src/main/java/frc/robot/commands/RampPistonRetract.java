package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RampPistonRetract extends Command {
  public RampPistonRetract() {
    requires(Robot.m_rampPiston);
  }

  @Override
  protected void initialize() {
    Robot.m_rampPiston.pistonRetract();
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
