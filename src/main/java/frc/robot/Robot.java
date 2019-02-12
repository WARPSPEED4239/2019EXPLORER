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
import frc.robot.subsystems.LiftWheelsBack;
import frc.robot.subsystems.LiftWheelsFront;
import frc.robot.subsystems.Ramps;
import frc.robot.subsystems.Wrist;
import frc.robot.tools.RGBController;

public class Robot extends TimedRobot {
  public static CargoIntake m_cargoIntake;
  public static DrivetrainShifting m_drivetrainShifting;
  public static Elevator m_elevator;
  public static HatchGrabber m_hatchGrabber;
  public static Drivetrain m_drivetrain;
  public static LiftWheelsBack m_liftWheelsBack;
  public static LiftWheelsFront m_liftWheelsFront;
  public static RGBController m_rgbController;
  public static Ramps m_ramps;
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
    m_liftWheelsBack = new LiftWheelsBack();
    m_liftWheelsFront = new LiftWheelsFront();
    m_rgbController = new RGBController(new CANifier(RobotMap.elevatorCanifier));
    m_ramps = new Ramps();
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
    SmartDashboard.putNumber("Elevator Position", m_elevator.getPositionInInches());
    SmartDashboard.putNumber("Elevator Velocity", m_elevator.getVelocityInInchesPerSecond());
    SmartDashboard.putBoolean("Elevator Bottom Limit Switch", m_elevator.getBottomLimitSwitch());
    SmartDashboard.putBoolean("Elevator Top Limit Switch", m_elevator.getTopLimitSwitch());

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
  
    /*if (m_oi.xbox.getBackButton()) {
     m_drivetrain.resetSensors();
    }*/
  }

  @Override
  public void testPeriodic() {
  }
}
