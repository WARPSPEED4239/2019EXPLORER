package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.States;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.commands.ElevatorSetPosition;
import frc.robot.commands.WristEndgame;
import frc.robot.commands.WristSetPercentOutput;
import frc.robot.commands.WristSetPosition;

public class GoToPosition extends CommandGroup {

  public GoToPosition(States.Positions positions) {
    double targetElevatorPosition = 0.0;
    double targetWristPosition = 0.0;
    boolean unkownState = false;
    boolean endgame = false;

   switch (positions) {
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
      targetWristPosition = 90.0;
      break;
    case CargoRocketOne:
      targetElevatorPosition = 27.0;
      targetWristPosition = 0.0;
      break;
    case CargoRocketTwo:
      targetElevatorPosition = 52.0;
      targetWristPosition = 0.0;
      break;
    case CargoRocketThree:
      targetElevatorPosition = 69.0;
      targetWristPosition = 50.0;
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
    case EndgamePrep:
      endgame = true;
      targetElevatorPosition = 24.0;
      targetWristPosition = 0.0;
      break;
    case EndgameDown:
      endgame = true;
      targetElevatorPosition = 0.0;
      targetWristPosition = 0.0;
    case Estop:
      unkownState = true;
      break;
    default:
      unkownState = true;
      break;
    }

    if (unkownState) {
      addParallel(new ElevatorSetPercentOutput(0.0));
      addSequential(new WristSetPercentOutput(0.0));
    } 
    else if (endgame) {
      addParallel(new ElevatorSetPosition(targetElevatorPosition));
      addSequential(new WristEndgame(targetWristPosition));
    }
    else {
      addParallel(new ElevatorSetPosition(targetElevatorPosition));
      addSequential(new WristSetPosition(targetWristPosition));
    }
  }
}