package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorManualControl extends Command {

  public ElevatorManualControl() {
    requires(Robot.m_elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    double output;
    output = 0;//-Robot.m_oi.getBoard().getY();

      if (Robot.m_elevator.getBottomLimitSwitch() && output < 0.0) {
        output = 0.0;
      }
      else if (Robot.m_elevator.getTop2To1LimitSwitch() && Robot.m_elevator.getTop3To2LimitSwitch() && output > 0.0) {
        output = 0.0;
      }
      Robot.m_elevator.setPercentOutput(output);
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
