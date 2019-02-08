package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class LiftOperation extends CommandGroup {
  
  public LiftOperation() {
    if (Robot.m_lift.getLiftIsZeroed() == false) {
      addSequential(new LiftZeroPosition());
    }

    if (Robot.m_lift.getLiftIsZeroed()) {
      
    }
  }
}
