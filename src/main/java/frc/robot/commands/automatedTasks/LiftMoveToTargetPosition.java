package frc.robot.commands.automatedTasks;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftMoveToTargetPosition extends Command {
  public LiftMoveToTargetPosition() {
    requires(Robot.m_lift);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_lift.liftSetPosition();
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
