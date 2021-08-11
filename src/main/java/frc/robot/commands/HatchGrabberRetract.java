package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchGrabberRetract extends Command {
  public HatchGrabberRetract() {
    requires(Robot.m_hatchGrabber);
  }

  @Override
  protected void initialize() {
    Robot.m_hatchGrabber.hatchGrabberRetract();
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