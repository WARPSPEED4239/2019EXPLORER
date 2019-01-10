package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelsFrontDown extends Command {
  public LiftWheelsFrontDown() {
    requires(Robot.m_liftWheelsFront);
  }

  @Override
  protected void initialize() {
    Robot.m_liftWheelsFront.liftWheelsPistonFrontDown();
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
