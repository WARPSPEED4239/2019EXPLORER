package frc.robot.commands.automatedTasks;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftMoveToTargetPosition extends Command {
  public LiftMoveToTargetPosition() {
    requires(Robot.m_lift);
  }

  @Override
  protected void initialize() {
    if (Robot.m_lift.getLiftIsZeroed() == false) {
      Robot.m_lift.zeroLiftPosition();
    }
  }

  @Override
  protected void execute() {
    Robot.m_lift.liftMoveTargetToPosition();
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
  }
}
