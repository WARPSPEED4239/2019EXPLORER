package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.States;

public class LiftZeroPosition extends Command {

  private double mTimeout;

  public LiftZeroPosition(double timeout) {
    requires(Robot.m_lift);
    mTimeout = timeout;
  }

  @Override
  protected void initialize() {
    if (Robot.m_lift.getLiftIsZeroed()) {
      end();
    }

    setTimeout(mTimeout);
  }

  @Override
  protected void execute() {
    Robot.m_lift.zeroLiftPosition();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_lift.getLiftLimitSwitch() || isTimedOut();
  }

  @Override
  protected void end() {
    if (Robot.m_lift.getLiftLimitSwitch()) {
      Robot.m_lift.liftStop();
      Robot.m_lift.zeroLiftPositionSensor();
    }
    if (isTimedOut()) {
      States.liftOperationState.MANUEL;
    }
  }

  @Override
  protected void interrupted() {
  }
}