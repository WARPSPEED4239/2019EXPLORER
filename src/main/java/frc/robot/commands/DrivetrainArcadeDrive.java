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
  }

  @Override
  protected void execute() {
    XboxController controller = Robot.m_oi.xbox;

    double move = -controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft);
    double rotate = -controller.getX(Hand.kLeft);

    Robot.m_drivetrain.arcadeDrive(move, rotate);

    /*SmartDashboard.putNumber("Left Position (ft)", Robot.m_drivetrain.getLeftEncoderPosition());
    SmartDashboard.putNumber("Right Position (ft)", Robot.m_drivetrain.getRightEncoderPosition());

    SmartDashboard.putNumber("Left Velocity (ft/s)", Robot.m_drivetrain.getLeftEncoderVelocity());
    SmartDashboard.putNumber("Right Velocity (ft/s)", Robot.m_drivetrain.getRightEncoderVelocity());
    
    Robot.m_drivetrain.getIMUdata();*/
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
