package frc.robot;

import com.ctre.phoenix.CANifier;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifting;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchGrabber;
import frc.robot.subsystems.LiftRodBack;
import frc.robot.subsystems.LiftRodsFront;
import frc.robot.subsystems.Wrist;
import frc.robot.tools.PneumaticController;
import frc.robot.tools.RGBController;

public class Robot extends TimedRobot {
  public static CargoIntake m_cargoIntake;
  public static DrivetrainShifting m_drivetrainShifting;
  public static Elevator m_elevator;
  public static HatchGrabber m_hatchGrabber;
  public static Drivetrain m_drivetrain;
  public static LiftRodBack m_liftRodBack;
  public static LiftRodsFront m_liftRodsFront;
  public static PneumaticController m_PneumaticController;
  public static RGBController m_rgbController;
  public static Wrist m_wrist;
  public static OI m_oi;

  private Command m_autonomousCommand;

  @Override
  public void robotInit() {
    m_cargoIntake = new CargoIntake();
    m_drivetrain = new Drivetrain();
    m_drivetrainShifting = new DrivetrainShifting();
    m_elevator = Elevator.create();
    m_hatchGrabber = new HatchGrabber();
    m_liftRodBack = new LiftRodBack();
    m_liftRodsFront = new LiftRodsFront();
    m_PneumaticController = PneumaticController.create();
    m_rgbController = new RGBController(new CANifier(RobotMap.canifier));
    m_wrist = Wrist.create();
    m_oi = new OI();

    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
    cam0.setResolution(320, 240);
    cam0.setFPS(10);
  }

  @Override
  public void robotPeriodic() {
    if (Robot.m_wrist.getBottomLimitSwitch()) {
      Robot.m_wrist.setEncoderValueInDegrees(0.0);
    }
    else if (Robot.m_wrist.getTopLimitSwitch()) {
      Robot.m_wrist.setEncoderValueInDegrees(145.7226);
    }

    if (Robot.m_elevator.getBottomLimitSwitch()) {
      Robot.m_elevator.setEncoderValueInInches(0.0);
    }
    else if (Robot.m_elevator.getTop2To1LimitSwitch() && Robot.m_elevator.getTop3To2LimitSwitch()) {
      Robot.m_elevator.setEncoderValueInInches(67.33019938);
    }

    SmartDashboard.putNumber("Left Position (ft)", Robot.m_drivetrain.getLeftEncoderPosition());
    SmartDashboard.putNumber("Right Position (ft)", Robot.m_drivetrain.getRightEncoderPosition());
    SmartDashboard.putNumber("Left Velocity (ft/s)", Robot.m_drivetrain.getLeftEncoderVelocity());
    SmartDashboard.putNumber("Right Velocity (ft/s)", Robot.m_drivetrain.getRightEncoderVelocity());
    
    SmartDashboard.putNumber("Elevator Position", m_elevator.getPositionInInches());
    SmartDashboard.putNumber("Elevator Velocity", m_elevator.getVelocityInInchesPerSecond());
    SmartDashboard.putBoolean("Elevator Bottom Limit Switch", m_elevator.getBottomLimitSwitch());
    SmartDashboard.putBoolean("Elevator Top Limit Switch", m_elevator.getTop2To1LimitSwitch() && m_elevator.getTop3To2LimitSwitch());
    
    SmartDashboard.putNumber("Wrist Position", m_wrist.getPositionInDegrees());
    SmartDashboard.putNumber("Wrist Velocity", m_wrist.getVelocityInDegreesPerSecond());
    SmartDashboard.putBoolean("Wrist Bottom Limit Switch", m_wrist.getBottomLimitSwitch());
    SmartDashboard.putBoolean("Wrist Top Limit Switch", m_wrist.getTopLimitSwitch());
    
    SmartDashboard.putNumber("Tank Pressure", m_PneumaticController.getTankPressure());
    SmartDashboard.putNumber("Pressure Sensor Volts", m_PneumaticController.getPressureSensorVolts());
    SmartDashboard.putBoolean("Compressor Status", m_PneumaticController.getCompressorStatus());

    SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
    SmartDashboard.putBoolean("DS Attached", DriverStation.getInstance().isDSAttached());
    SmartDashboard.putBoolean("FMS Attached", DriverStation.getInstance().isFMSAttached());
    SmartDashboard.putBoolean("Browned Out", RobotController.isBrownedOut());
    SmartDashboard.putBoolean("Sys Active", RobotController.isSysActive());

    if (Constants.kCodeTestingState) {

      SmartDashboard.putBoolean("Elevator Top 2 to 1 Limit Switch", m_elevator.getTop2To1LimitSwitch());
      SmartDashboard.putBoolean("Elevator Top 3 to 2 Limit Switch", m_elevator.getTop3To2LimitSwitch());
      
      if (m_oi.xbox.getPOV() == 0) {
        m_drivetrain.resetSensors();
       }
   
       if (m_oi.xbox.getPOV() == 90) {
         m_wrist.zeroEncoder();
       }
   
       if (m_oi.xbox.getPOV() == 270) {
         m_elevator.zeroEncoder();
       }
    }
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

    /*if (m_PneumaticController.getTankPressure() <= 65.0 && RobotController.getBatteryVoltage() >= 12.2) {
      m_PneumaticController.turnOnCompressor();
    } else if (m_PneumaticController.getTankPressure() < 55.0 && RobotController.getBatteryVoltage() > 11.6 && RobotController.getBatteryVoltage() < 12.2) {
      m_PneumaticController.turnOnCompressor();
    } else if (m_PneumaticController.getTankPressure() >= 55.0 && RobotController.getBatteryVoltage() > 11.6 && RobotController.getBatteryVoltage() < 12.0) {
      m_PneumaticController.turnOffCompressor();
    } else if (m_PneumaticController.getTankPressure() >= 80.0 || RobotController.getBatteryVoltage() <= 11.6) {
      m_PneumaticController.turnOffCompressor();
    }*/
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

    /*if (m_PneumaticController.getTankPressure() <= 65.0 && RobotController.getBatteryVoltage() >= 12.2) {
      m_PneumaticController.turnOnCompressor();
    } else if (m_PneumaticController.getTankPressure() < 55.0 && RobotController.getBatteryVoltage() > 11.6 && RobotController.getBatteryVoltage() < 12.2) {
      m_PneumaticController.turnOnCompressor();
    } else if (m_PneumaticController.getTankPressure() >= 55.0 && RobotController.getBatteryVoltage() > 11.6 && RobotController.getBatteryVoltage() < 12.0) {
      m_PneumaticController.turnOffCompressor();
    } else if (m_PneumaticController.getTankPressure() >= 80.0 || RobotController.getBatteryVoltage() <= 11.6) {
      m_PneumaticController.turnOffCompressor();
    }*/

  }

  @Override
  public void testPeriodic() {
    m_PneumaticController.turnOnCompressor();
  }
}
