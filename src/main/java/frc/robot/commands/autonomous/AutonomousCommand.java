package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.States.StartingConfig;

public class AutonomousCommand extends CommandGroup {

  public AutonomousCommand(StartingConfig startingConfig) {
    switch (startingConfig) {
    case HatchPannel:
      /*addParallel(new HatchGrabberExtend());
      addSequential(new GoToPosition(States.Positions.Floor)); //TODO MAKE THIS END (Might not be a thing, talk to build)
      addSequential(new GoToPosition(States.Positions.HatchLevelOne));*/
      break;
    case Cargo:
      break;
    default:
      break;
    }
  }
}
