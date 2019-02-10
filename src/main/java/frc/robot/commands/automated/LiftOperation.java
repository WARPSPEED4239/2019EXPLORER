package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class LiftOperation extends CommandGroup {

  public LiftOperation() {
    requires(Robot.m_lift);

    if (Robot.m_lift.getLiftIsZeroed() == false) {
      if (Robot.m_oi.getBoard().getRawButton(1)) {
        addSequential(new LiftZeroPosition(5.0));
      } else if (Robot.m_oi.getBoard().getRawButton(2)) {
        addSequential(new LiftZeroPosition(5.0));
      } else if (Robot.m_oi.getBoard().getRawButton(3)) {
        addSequential(new LiftZeroPosition(5.0));
      } else if (Robot.m_oi.getBoard().getRawButton(4)) {
        addSequential(new LiftZeroPosition(5.0));
      } else if (Robot.m_lift.getLiftLimitSwitch()) {
        Robot.m_lift.liftStop();
        Robot.m_lift.zeroLiftPositionSensor();
      }
    }

    if (Robot.m_lift.getLiftIsZeroed()) {
      if (Robot.m_oi.getBoard().getRawButton(1)) {
        Robot.m_lift.liftMoveTargetToPosition(5000);
      } else if (Robot.m_oi.getBoard().getRawButton(2)) {
        Robot.m_lift.liftMoveTargetToPosition(400);
      } else if (Robot.m_oi.getBoard().getRawButton(3)) {
        Robot.m_lift.liftMoveTargetToPosition(10000);
      } else if (Robot.m_oi.getBoard().getRawButton(4)) {
        Robot.m_lift.liftMoveTargetToPosition(0);
      } else if (Robot.m_lift.getLiftLimitSwitch()) {
        Robot.m_lift.liftStop();
        Robot.m_lift.zeroLiftPositionSensor();
      }
    }
  }
}
