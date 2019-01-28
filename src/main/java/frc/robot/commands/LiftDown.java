package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftDown extends Command {
  public LiftDown() {
    requires(Robot.m_lift);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_lift.liftDown();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_lift.getLiftLimitSwitch();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
