package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftWheelsMotorStop extends Command {
  public LiftWheelsMotorStop() {
    requires(Robot.m_liftWheelsMotor);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_liftWheelsMotor.stop();
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
