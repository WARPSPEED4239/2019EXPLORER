package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DrivetrainArcadeDrive extends Command {
  public DrivetrainArcadeDrive() {
    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
    Robot.m_drivetrain.setIsAuto(false);
  }

  @Override
  protected void execute() {
    XboxController controller = Robot.m_oi.xbox;

    double move = -controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft);
    double rotate = -controller.getX(Hand.kLeft);

    Robot.m_drivetrain.arcadeDrive(move, rotate);
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
