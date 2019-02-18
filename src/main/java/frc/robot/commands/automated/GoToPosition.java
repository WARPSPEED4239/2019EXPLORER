package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.States;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.commands.ElevatorSetPosition;
import frc.robot.commands.WristSetPercentOutput;
import frc.robot.commands.WristSetPosition;

public class GoToPosition extends CommandGroup {
  
  public GoToPosition(States.Positions positions) {
    double targetElevatorPosition = 0;
    double targetWristPosition = 0;
    boolean unkownState = false;

    switch (positions) {
      case HatchLevelOne:
        targetElevatorPosition = 0;
        targetWristPosition = 90;
        break;
      case HatchLevelTwo:
        targetElevatorPosition = 30.0;
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
      case CargoFloor:
        targetElevatorPosition = 15;
        targetWristPosition = 0;
        break;
      case CargoPlayerStationAndCargoShip:
        targetElevatorPosition = 40;
        targetWristPosition = 0;
        break;
      case StoredPosition:
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
    }
  }

