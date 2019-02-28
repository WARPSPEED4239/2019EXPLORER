package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DrivetrainArcadeDrive extends Command {
  public DrivetrainArcadeDrive() {
    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double move = -Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) + Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft);
    double rotate = Math.pow((-Robot.m_oi.xbox.getX(Hand.kLeft)), 1.4); //CHANGE 1.4 IF NECCESSARY (CHECK DESMOS)

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
