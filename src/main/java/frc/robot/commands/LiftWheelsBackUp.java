package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelsBackUp extends Command {
  public LiftWheelsBackUp() {
    requires(Robot.m_liftWheelsBack);
  }

  @Override
  protected void initialize() {
    Robot.m_liftWheelsBack.liftWheelsPistonBackUp();
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
