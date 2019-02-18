package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristSetPercentOutput extends Command {

  private double mOutput;
  public WristSetPercentOutput(double output) {
    requires(Robot.m_wrist);

    mOutput = output;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_wrist.setPercentOutput(mOutput);
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
