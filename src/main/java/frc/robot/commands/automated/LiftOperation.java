package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class LiftOperation extends CommandGroup {

  public LiftOperation() {
    if (Robot.m_lift.getLiftIsZeroed() == false) {
      if (Robot.m_oi.bButton1.get()) {
        addSequential(new LiftZeroPosition());
      } else if (Robot.m_oi.bButton2.get()) {
        addSequential(new LiftZeroPosition());
      } else if (Robot.m_oi.bButton3.get()) {
        addSequential(new LiftZeroPosition());
      } else if (Robot.m_oi.bButton4.get()) {
        addSequential(new LiftZeroPosition());
      } else if (Robot.m_lift.getLiftLimitSwitch()) {
        Robot.m_lift.liftStop();
        Robot.m_lift.zeroLiftPositionSensor();
      }
    }

    if (Robot.m_lift.getLiftIsZeroed()) {
      if (Robot.m_oi.bButton1.get()) {
        Robot.m_lift.liftMoveTargetToPosition(5000);
      } else if (Robot.m_oi.bButton2.get()) {
        Robot.m_lift.liftMoveTargetToPosition(400);
      } else if (Robot.m_oi.bButton3.get()) {
        Robot.m_lift.liftMoveTargetToPosition(10000);
      } else if (Robot.m_oi.bButton4.get()) {
        Robot.m_lift.liftMoveTargetToPosition(0);
      } else if (Robot.m_lift.getLiftLimitSwitch()) {
        Robot.m_lift.liftStop();
        Robot.m_lift.zeroLiftPositionSensor();
      }
    }
  }
}
