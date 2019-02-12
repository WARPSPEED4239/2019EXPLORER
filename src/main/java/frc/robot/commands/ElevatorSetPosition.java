package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorSetPosition extends Command {

  private final double mPositionInInches;

  public ElevatorSetPosition(double positionInInches) {
    requires(Robot.m_elevator);

    mPositionInInches = positionInInches;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_elevator.getBottomLimitSwitch() && Robot.m_elevator.getVelocityInInchesPerSecond() <= 0.0) {
      Robot.m_elevator.setPercentOutput(0.0);
    } else if (Robot.m_elevator.getTopLimitSwitch() && Robot.m_elevator.getVelocityInInchesPerSecond() >= 0.0) {
      Robot.m_elevator.setPercentOutput(0.0);
    } else {
      Robot.m_elevator.setPosition(mPositionInInches); // If this doesn't work, call only this
    }
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
