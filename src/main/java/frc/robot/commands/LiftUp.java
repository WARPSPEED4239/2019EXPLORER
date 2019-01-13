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
    return Robot.m_lift.getTopSwitch();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
