package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
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
    if (Robot.m_elevator.getTop2To1LimitSwitch() && Robot.m_elevator.getTop3To2LimitSwitch() && Robot.m_elevator.getMotorOutputVoltage() > Constants.kEpsilson) {
      Robot.m_elevator.setPercentOutput(0.0);
    } else if (Robot.m_elevator.getBottomLimitSwitch() && Robot.m_elevator.getMotorOutputVoltage() < -Constants.kEpsilson) {
      Robot.m_elevator.setPercentOutput(0.0);
    } else {
      Robot.m_elevator.setPosition(mPositionInInches);
    }
    SmartDashboard.putNumberArray("Elevator Target Position", new double [] {Robot.m_elevator.getActiveTrajetoryPositionInInches(), Robot.m_elevator.getPositionInInches()});
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
