package frc.robot.commands.automated;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DrivetrainVisionAssist extends Command {

  NetworkTableEntry tx = Robot.m_drivetrain.getLimelightTable().getEntry("tx");
  NetworkTableEntry tv = Robot.m_drivetrain.getLimelightTable().getEntry("tv");

  private final double Kp = 0.0335;

  public DrivetrainVisionAssist() {
    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
    Robot.m_drivetrain.getLimelightTable().getEntry("pipeline").setNumber(0);
    Robot.m_drivetrain.getLimelightTable().getEntry("camMode").setNumber(0);
    Robot.m_drivetrain.getLimelightTable().getEntry("ledMode").setNumber(3);
  }

  @Override
  protected void execute() {
    double v = tv.getDouble(0.0);

    if (v == 1.0) {
      double x = tx.getDouble(0.0);
      double rotate;
      double move = -Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) + Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft);

      if (Math.abs(x) < 1.0) {
        rotate = 0;
      } else {
        rotate = Kp * -x;
      }
      
      Robot.m_drivetrain.driveWithVisionAssist(move, rotate);
    } else {
      double move = -Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) + Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft);
      double rotate = -(.533333 * Math.pow(Robot.m_oi.xbox.getX(Hand.kLeft), 3) + .466666 *  Robot.m_oi.xbox.getX(Hand.kLeft));
      
      Robot.m_drivetrain.arcadeDrive(move, rotate);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_drivetrain.getLimelightTable().getEntry("camMode").setNumber(1);
    Robot.m_drivetrain.getLimelightTable().getEntry("ledMode").setNumber(1);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
