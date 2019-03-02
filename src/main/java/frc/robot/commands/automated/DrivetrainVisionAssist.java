package frc.robot.commands.automated;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DrivetrainVisionAssist extends Command {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  private final double Kp = 0.0; //TODO SET THIS NUMBER

  public DrivetrainVisionAssist() {
    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double x = tx.getDouble(0.0);

    double move = -Robot.m_oi.xbox.getTriggerAxis(Hand.kRight) + Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft);;
    double rotate = Kp * x;

    Robot.m_drivetrain.driveWithVisionAssist(move, rotate);
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
