package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftRodsFrontRetract extends Command {
  public LiftRodsFrontRetract() {
    requires(Robot.m_liftRodsFront);
  }

  @Override
  protected void initialize() {
    Robot.m_liftRodsFront.retract();
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
