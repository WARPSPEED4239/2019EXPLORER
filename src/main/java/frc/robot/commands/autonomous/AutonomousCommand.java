package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.States;
import frc.robot.States.StartingConfig;
import frc.robot.commands.HatchGrabberExtend;
import frc.robot.commands.automated.GoToPosition;

public class AutonomousCommand extends CommandGroup {

  public AutonomousCommand(StartingConfig startingConfig) {
    switch (startingConfig) {
    case HatchPannel:
      addParallel(new HatchGrabberExtend());
      addSequential(new GoToPosition(States.Positions.Floor)); //TODO MAKE THIS END
      addSequential(new GoToPosition(States.Positions.HatchLevelOne));
      break;
    case Cargo:
      break;
    default:
      break;
    }
  }
}
