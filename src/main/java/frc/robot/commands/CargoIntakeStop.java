package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoIntakeStop extends Command {
  public CargoIntakeStop() {
    requires(Robot.m_cargoIntake);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_cargoIntake.stop();
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
