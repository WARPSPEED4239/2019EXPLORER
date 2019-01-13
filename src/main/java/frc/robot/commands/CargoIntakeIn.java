package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoIntakeIn extends Command {
  public CargoIntakeIn() {
    requires(Robot.m_cargoIntake);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_cargoIntake.cargoIntakeIn();
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
