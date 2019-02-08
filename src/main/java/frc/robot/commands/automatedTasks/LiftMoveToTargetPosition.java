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
    if (Robot.m_oi.bButton1.get()) {
      Robot.m_lift.liftMoveTargetToPosition(5000);
    }
    else if (Robot.m_oi.bButton2.get()) {
      Robot.m_lift.liftMoveTargetToPosition(400);
    }

    else if (Robot.m_oi.bButton3.get()) {
      Robot.m_lift.liftMoveTargetToPosition(10000);
    }
    else if (Robot.m_oi.bButton4.get()) {
      Robot.m_lift.liftMoveTargetToPosition(0);
    }
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
