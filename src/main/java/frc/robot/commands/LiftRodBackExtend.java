package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftRodBackExtend extends Command {
  public LiftRodBackExtend() {
    requires(Robot.m_liftRodBack);
  }

  @Override
  protected void initialize() {
    Robot.m_liftRodBack.extend();
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
