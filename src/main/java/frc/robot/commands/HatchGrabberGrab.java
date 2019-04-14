package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchGrabberGrab extends Command {
  public HatchGrabberGrab() {
    requires(Robot.m_hatchGrabber);
  }

  @Override
  protected void initialize() {
    Robot.m_hatchGrabber.extend();
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
