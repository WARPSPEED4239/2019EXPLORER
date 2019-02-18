package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorSetPercentOutput extends Command {
    
private double mOutput;
  
  public ElevatorSetPercentOutput(double output) {
    requires(Robot.m_elevator);

    mOutput = output;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_elevator.setPercentOutput(mOutput);
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
