package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftZeroPosition extends Command {
  public LiftZeroPosition() {
    requires(Robot.m_lift);
  }

  @Override
  protected void initialize() {
    if (Robot.m_lift.getLiftIsZeroed()) {
      end();
    }
  }

  @Override
  protected void execute() {
    Robot.m_lift.zeroLiftPosition();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_lift.getLiftLimitSwitch();
  }

  @Override
  protected void end() {
    if (Robot.m_lift.getLiftLimitSwitch()) {
      Robot.m_lift.liftStop();
      Robot.m_lift.zeroLiftPositionSensor();
    }
  }

  @Override
  protected void interrupted() {
  }
}