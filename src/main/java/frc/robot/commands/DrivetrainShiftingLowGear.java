package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DrivetrainShiftingLowGear extends Command {
  public DrivetrainShiftingLowGear() {
    requires(Robot.m_drivetrainShifting);
  }

  @Override
  protected void initialize() {
    Robot.m_drivetrainShifting.lowGear();
  }

  @Override
  protected void execute() {
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
    end();
  }
}
