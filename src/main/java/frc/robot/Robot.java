package frc.robot;

import com.ctre.phoenix.CANifier;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifting;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchGrabber;
import frc.robot.subsystems.LiftWheel;
import frc.robot.subsystems.RampPiston;
import frc.robot.subsystems.Ramps;
import frc.robot.subsystems.Wrist;
import frc.robot.tools.RGBController;

public class Robot extends TimedRobot {
  public static CargoIntake m_cargoIntake;
  public static DrivetrainShifting m_drivetrainShifting;
  public static Elevator m_elevator;
  public static HatchGrabber m_hatchGrabber;
  public static Drivetrain m_drivetrain;
  public static LiftWheel m_liftWheel;
  public static RGBController m_rgbController;
  public static Ramps m_ramps;
  public static RampPiston m_rampPiston;
  public static Wrist m_wrist;
  public static OI m_oi;

  private Command m_autonomousCommand;
  private SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_cargoIntake = new CargoIntake();
    m_drivetrainShifting = new DrivetrainShifting();
    m_elevator = Elevator.create();
    m_hatchGrabber = new HatchGrabber();
    m_drivetrain = new Drivetrain();
    m_liftWheel = new LiftWheel();
    m_rgbController = new RGBController(new CANifier(RobotMap.canifier));
    m_ramps = new Ramps();
    m_rampPiston = new RampPiston();
    m_wrist = Wrist.create();
    m_oi = new OI();
    
    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
    cam0.setResolution(320, 240);
    cam0.setFPS(10);
    
    m_chooser.setDefaultOption("Default Auto", new DrivetrainArcadeDrive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left Position (ft)", Robot.m_drivetrain.getLeftEncoderPosition());
    SmartDashboard.putNumber("Right Position (ft)", Robot.m_drivetrain.getRightEncoderPosition());
    SmartDashboard.putNumber("Left Velocity (ft/s)", Robot.m_drivetrain.getLeftEncoderVelocity());
    SmartDashboard.putNumber("Right Velocity (ft/s)", Robot.m_drivetrain.getRightEncoderVelocity());
    SmartDashboard.putNumber("Gyro Yaw (deg)", Robot.m_drivetrain.getIMUYaw());
    
    SmartDashboard.putNumber("Elevator Position", m_elevator.getPositionInInches());
    SmartDashboard.putNumber("Elevator Velocity", m_elevator.getVelocityInInchesPerSecond());
    SmartDashboard.putBoolean("Elevator Bottom Limit Switch", m_elevator.getBottomLimitSwitch());
    SmartDashboard.putBoolean("Elevator Top 2 to 1 Limit Switch", m_elevator.getTop2To1LimitSwitch());
    SmartDashboard.putBoolean("Elevator Top 3 to 2 Limit Switch", m_elevator.getTop3To2LimitSwitch());

    SmartDashboard.putNumber("Wrist Position", m_wrist.getPositionInDegrees());
    SmartDashboard.putNumber("Wrist Velocity", m_wrist.getVelocityInDegreesPerSecond());
    SmartDashboard.putBoolean("Wrist Bottom Limit Switch", m_wrist.getBottomLimitSwitch());
    SmartDashboard.putBoolean("Wrist Top Limit Switch", m_wrist.getTopLimitSwitch());
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
     
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  
    if (m_oi.xbox.getBackButton()) {
     m_drivetrain.resetSensors();
    }

    if (m_oi.xbox.getStartButton()) {
      m_wrist.resetEncoder();
    }
  }

  @Override
  public void testPeriodic() {
  }
}
