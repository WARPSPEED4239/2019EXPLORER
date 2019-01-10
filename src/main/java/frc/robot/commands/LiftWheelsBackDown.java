package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelsBackDown extends Command {
  public LiftWheelsBackDown() {
    requires(Robot.m_liftWheelsBack);
  }

  @Override
  protected void initialize() {
    Robot.m_liftWheelsBack.liftWheelsPistonBackDown();
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
