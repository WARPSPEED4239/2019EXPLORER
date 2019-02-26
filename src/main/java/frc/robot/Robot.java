package frc.robot;

import com.ctre.phoenix.CANifier;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
<<<<<<< HEAD
<<<<<<< HEAD
import frc.robot.States.StartingConfig;
import frc.robot.commands.autonomous.AutonomousCommand;
=======
=======
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
import frc.robot.commands.DrivetrainArcadeDrive;
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifting;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchGrabber;
import frc.robot.subsystems.LiftWheelsMotor;
import frc.robot.subsystems.LiftWheelsPiston;
import frc.robot.subsystems.Wrist;
import frc.robot.tools.RGBController;

public class Robot extends TimedRobot {
  public static CargoIntake m_cargoIntake;
  public static DrivetrainShifting m_drivetrainShifting;
  public static Elevator m_elevator;
  public static HatchGrabber m_hatchGrabber;
  public static Drivetrain m_drivetrain;
  public static LiftWheelsMotor m_liftWheelsMotor;
  public static LiftWheelsPiston m_liftWheelsPiston;
  public static RGBController m_rgbController;
  public static Wrist m_wrist;
  public static OI m_oi;

  private Command m_autonomousCommand;
<<<<<<< HEAD
<<<<<<< HEAD
  private SendableChooser<StartingConfig> m_startingConfigChooser = new SendableChooser<>();
=======
  private SendableChooser<Command> m_chooser = new SendableChooser<>();
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
=======
  private SendableChooser<Command> m_chooser = new SendableChooser<>();
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster

  @Override
  public void robotInit() {
    m_cargoIntake = new CargoIntake();
    m_drivetrainShifting = new DrivetrainShifting();
    m_elevator = Elevator.create();
    m_hatchGrabber = new HatchGrabber();
    m_drivetrain = new Drivetrain();
    m_liftWheelsMotor = new LiftWheelsMotor();
    m_liftWheelsPiston = new LiftWheelsPiston();
    m_rgbController = new RGBController(new CANifier(RobotMap.canifier));
    m_wrist = Wrist.create();
    m_oi = new OI();
    
    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
    cam0.setResolution(320, 240);
    cam0.setFPS(10);
    
<<<<<<< HEAD
<<<<<<< HEAD
    m_startingConfigChooser.setDefaultOption("Hatch Pannel", StartingConfig.HatchPannel);
    m_startingConfigChooser.addOption("Cargo", StartingConfig.Cargo);
    SmartDashboard.putData("Starting Config", m_startingConfigChooser);
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
    m_chooser.setDefaultOption("Default Auto", new DrivetrainArcadeDrive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
=======
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.

    m_wrist.resetEncoder(); //TODO Take these out
    m_elevator.resetEncoder();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left Position (ft)", Robot.m_drivetrain.getLeftEncoderPosition());
    SmartDashboard.putNumber("Right Position (ft)", Robot.m_drivetrain.getRightEncoderPosition());
    SmartDashboard.putNumber("Left Velocity (ft/s)", Robot.m_drivetrain.getLeftEncoderVelocity());
    SmartDashboard.putNumber("Right Velocity (ft/s)", Robot.m_drivetrain.getRightEncoderVelocity());
    SmartDashboard.putNumber("Gyro Yaw (deg)", Robot.m_drivetrain.getIMURoll());
    
    SmartDashboard.putNumber("Elevator Position", m_elevator.getPositionInInches());
    SmartDashboard.putNumber("Elevator Velocity", m_elevator.getVelocityInInchesPerSecond());
    SmartDashboard.putBoolean("Elevator Bottom Limit Switch", m_elevator.getBottomLimitSwitch());
    SmartDashboard.putBoolean("Elevator Top 2 to 1 Limit Switch", m_elevator.getTop2To1LimitSwitch());
    SmartDashboard.putBoolean("Elevator Top 3 to 2 Limit Switch", m_elevator.getTop3To2LimitSwitch());

    SmartDashboard.putNumber("Wrist Position", m_wrist.getPositionInDegrees());
    SmartDashboard.putNumber("Wrist Velocity", m_wrist.getVelocityInDegreesPerSecond());
    SmartDashboard.putBoolean("Wrist Bottom Limit Switch", m_wrist.getBottomLimitSwitch());
    SmartDashboard.putBoolean("Wrist Top Limit Switch", m_wrist.getTopLimitSwitch());

    SmartDashboard.putData("Elevator Command", m_elevator);
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
<<<<<<< HEAD
<<<<<<< HEAD
    StartingConfig startingConfig = m_startingConfigChooser.getSelected();
    m_autonomousCommand = new AutonomousCommand(startingConfig);
=======
    m_autonomousCommand = m_chooser.getSelected();
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
=======
    m_autonomousCommand = m_chooser.getSelected();
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
     
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

    if(m_oi.xbox.getBumper(Hand.kRight)) {
      m_elevator.resetEncoder();
    }

  }

  @Override
  public void testPeriodic() {
  }
}
