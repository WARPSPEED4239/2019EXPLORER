package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.CommandGroup;
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
    boolean runSequentially = false;

    switch (positions) {
<<<<<<< HEAD
    case HatchLevelOne:
      targetElevatorPosition = 0.0;
      targetWristPosition = 90.0;
      break;
    case HatchLevelTwo:
      targetElevatorPosition = 30.0;
      targetWristPosition = 90.0;
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
    case Floor: //TODO Implement HatchGrabberRetract when called?
      targetElevatorPosition = 0.0;
      targetWristPosition = 0.0;
      break;
    case Stored:
      runSequentially = true;
      targetElevatorPosition = 0.0;
      targetWristPosition = 146.3375;
      break;
    case Estop:
      unkownState = true;
      break;
    default:
      unkownState = true;
      break;
=======
      case HatchLevelOne:
        targetElevatorPosition = 0;
        targetWristPosition = 90;
        break;
      case HatchLevelTwo:
        targetElevatorPosition = 30.0; // SET
        targetWristPosition = 90;
        break;
      case HatchLevelThree:
        targetElevatorPosition = 60;
        targetWristPosition = 85;
        break;
      case HatchFloor:
        targetElevatorPosition = 0;
        targetWristPosition = 0;
        break;
      case CargoRocketOne:
        targetElevatorPosition = 0;
        targetWristPosition = 60;
        break;
      case CargoRocketTwo:
        targetElevatorPosition = 35;
        targetWristPosition = 60;
        break;
      case CargoRocketThree:
        targetElevatorPosition = 65;
        targetWristPosition = 60;
        break;
      case CargoCargoShip:
        targetElevatorPosition = 30;
        targetWristPosition = 60;
        break;
      case CargoFloor:
        targetElevatorPosition = 10;
        targetWristPosition = 0;
        break;
      case CargoPlayerStation:
        targetElevatorPosition = 37;
        targetWristPosition = 20;
        break;
      case StartingPosition:
        targetElevatorPosition = 0;
        targetWristPosition = 0;//146;
        break;
  
      default:
        unkownState = true;
        break;
      }
    
      if (unkownState) {
        addParallel(new WristSetPercentOutput(0));
        addSequential(new ElevatorSetPercentOutput(0));
      }
      else {
        addParallel(new WristSetPosition(targetWristPosition));
        addSequential(new ElevatorSetPosition(targetElevatorPosition));
      }
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
    }

    if (unkownState) {
      addParallel(new ElevatorSetPercentOutput(0.0));
      addSequential(new WristSetPercentOutput(0.0));
    } else if (runSequentially) { // TODO Implememt So ElevatorSetPosoiton ends
      addSequential(new ElevatorSetPosition(targetElevatorPosition));
      addSequential(new WristSetPosition(targetWristPosition));
      runSequentially = false;
    } else {
      addParallel(new ElevatorSetPosition(targetElevatorPosition));
      addSequential(new WristSetPosition(targetWristPosition));
    }
  }
}
