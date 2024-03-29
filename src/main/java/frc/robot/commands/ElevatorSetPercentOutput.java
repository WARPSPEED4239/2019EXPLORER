package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorSetPercentOutput extends Command {

  private double mOutput;

  public ElevatorSetPercentOutput(double output) {
    requires(Robot.m_elevator);

    mOutput = output;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    /*if (Robot.m_elevator.getBottomLimitSwitch() && mOutput < 0.0) {
      mOutput = 0.0;
    } else if (Robot.m_elevator.getTop2To1LimitSwitch() && Robot.m_elevator.getTop3To2LimitSwitch() && mOutput > 0.0) {
      mOutput = 0.0;
    }*/

    Robot.m_elevator.setPercentOutput(mOutput);
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
