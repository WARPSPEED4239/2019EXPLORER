package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftRodsFrontExtend extends Command {
  public LiftRodsFrontExtend() {
    requires(Robot.m_liftRodsFront);
  }

  @Override
  protected void initialize() {
    Robot.m_liftRodsFront.extend();
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
