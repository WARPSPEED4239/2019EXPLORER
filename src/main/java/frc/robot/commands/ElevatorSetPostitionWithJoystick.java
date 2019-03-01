package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class ElevatorSetPostitionWithJoystick extends Command {
  public ElevatorSetPostitionWithJoystick() {
    requires(Robot.m_elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    String elevatorState = "unknown";

    if (Constants.kCodeTestingState) {
      if (Robot.m_elevator.getBottomLimitSwitch() && Robot.m_elevator.getVelocityInInchesPerSecond() < -Constants.kEpsilson) {
        elevatorState = "bottom error";
        Robot.m_elevator.setPercentOutput(0.0);
        Robot.m_elevator.setEncoderValueInInches(0.0);
      } else if (Robot.m_elevator.getTop2To1LimitSwitch() && Robot.m_elevator.getTop3To2LimitSwitch() && Robot.m_elevator.getVelocityInInchesPerSecond() > Constants.kEpsilson) {
        elevatorState = "top error";
        Robot.m_elevator.setPercentOutput(0.0);
        Robot.m_elevator.setEncoderValueInInches(67.33019938);
      } else {
        elevatorState = "no error";
        double targetPosition = 60 * -Robot.m_oi.getBoard().getY();
        if (targetPosition < 0 ) {
          targetPosition = 0;
        }
        Robot.m_elevator.setPosition(targetPosition);
        SmartDashboard.putNumberArray("Elevator Target Position", new double [] {Robot.m_elevator.getActiveTrajetoryPositionInInches(), Robot.m_elevator.getPositionInInches()});
      }
      SmartDashboard.putString("Elevator State", elevatorState); 
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
