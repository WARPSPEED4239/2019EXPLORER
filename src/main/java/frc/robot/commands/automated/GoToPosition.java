package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.States;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.commands.ElevatorSetPosition;
import frc.robot.commands.WristSetPercentOutput;
import frc.robot.commands.WristSetPosition;

public class GoToPosition extends CommandGroup {

  public GoToPosition(States.Positions positions) {
    double targetElevatorPosition = 0.0;
    double targetWristPosition = 0.0;
    boolean unkownState = false;

   switch (positions) {
    case HatchLevelOne:
      targetElevatorPosition = 0.0;
      targetWristPosition = 85.0;
      break;
    case HatchLevelTwo:
      targetElevatorPosition = 30.0;
      targetWristPosition = 85.0;
      break;
    case HatchLevelThree:
      targetElevatorPosition = 60.0;
      targetWristPosition = 85.0;
      break;
    case CargoRocketOne:
      targetElevatorPosition = 0.0;
      targetWristPosition = 60.0;
      break;
    case CargoRocketTwo:
      targetElevatorPosition = 35.0;
      targetWristPosition = 60.0;
      break;
    case CargoRocketThree:
      targetElevatorPosition = 67.0;
      targetWristPosition = 40.0;
      break;
    case CargoIntakeElevated:
      targetElevatorPosition = 15.0;
      targetWristPosition = 0.0;
      break;
    case CargoPlayerStationAndCargoShip:
      targetElevatorPosition = 40.0;
      targetWristPosition = 0.0;
      break;
    case Floor:
      targetElevatorPosition = 0.0;
      targetWristPosition = 0.0;
      break;
    case Stored:
      targetElevatorPosition = 0.0;
      targetWristPosition = 146.3375;
      break;
    case Estop:
      unkownState = true; //TODO RESET THIS BACK TO FALSE SOMEWHERE? (See Below)
      break;
    default:
      unkownState = true;
      break;
    }

    if (unkownState) {
      addParallel(new ElevatorSetPercentOutput(0.0));
      addSequential(new WristSetPercentOutput(0.0));
    } 
    else if (unkownState && Robot.m_elevator.getBottomLimitSwitch() && (Robot.m_wrist.getBottomLimitSwitch() || Robot.m_wrist.getTopLimitSwitch())) { //REVIEW, Does this fix the issue above? Will this need a double click of a button?
      unkownState = false;
      addParallel(new ElevatorSetPosition(targetElevatorPosition));
      addSequential(new WristSetPosition(targetWristPosition));
    }
    else {
      addParallel(new ElevatorSetPosition(targetElevatorPosition));
      addSequential(new WristSetPosition(targetWristPosition));
    }
  }
}
