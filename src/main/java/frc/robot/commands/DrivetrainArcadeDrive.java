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
    Robot.m_drivetrain.getLimelightTable().getEntry("pipeline").setNumber(0);
    Robot.m_drivetrain.getLimelightTable().getEntry("ledMode").setNumber(1);
  }

  @Override
  protected void execute() {
    double move = -Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) + Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft);
    //double move = (-(Math.pow(Robot.m_oi.xbox.getTriggerAxis(Hand.kRight), 2)) * 0.6) + (Math.pow(Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft), 2) * 0.6);

    double rotate = -(.533333 * Math.pow(Robot.m_oi.xbox.getX(Hand.kLeft), 3) + .466666 *  Robot.m_oi.xbox.getX(Hand.kLeft));

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
