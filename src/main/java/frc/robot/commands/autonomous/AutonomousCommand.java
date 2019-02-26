package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.States.StartingConfig;
import frc.robot.commands.HatchGrabberExtend;
import frc.robot.commands.WristSetPosition;

public class AutonomousCommand extends CommandGroup {

  public AutonomousCommand(StartingConfig startingConfig) {
    switch (startingConfig) {
    case HatchPannel:
      addParallel(new HatchGrabberExtend());
      addSequential(new WristSetPosition(0));
      addSequential(new WristSetPosition(90));
      break;
    case Cargo:
      break;
    default:
      break;
    }
  }
}
