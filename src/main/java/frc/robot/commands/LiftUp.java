package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUp extends Command {
  public LiftUp() {
    requires(Robot.m_lift);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_lift.liftUp();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_lift.liftStop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
