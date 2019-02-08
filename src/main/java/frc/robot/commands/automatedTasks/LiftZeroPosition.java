package frc.robot.commands.automatedTasks;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftZeroPosition extends Command {
  public LiftZeroPosition() {
    requires(Robot.m_lift);
  }

  @Override
  protected void initialize() {
    if (Robot.m_lift.getLiftIsZeroed() == true) {
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
    Robot.m_lift.zeroLiftPositionSensor();
  }

  @Override
  protected void interrupted() {
  }
}
