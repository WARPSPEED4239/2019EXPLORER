package frc.robot;

import com.ctre.phoenix.CANifier;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.subsystems.DrivetrainREV;
import frc.robot.subsystems.LiftWheelsBack;
import frc.robot.subsystems.LiftWheelsFront;
import frc.robot.tools.RGBController;

public class Robot extends TimedRobot {
  public static DrivetrainREV m_drivetrainREV;
  public static LiftWheelsBack m_liftWheelsBack;
  public static LiftWheelsFront m_liftWheelsFront;
  public static RGBController m_rgbController;
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_drivetrainREV = new DrivetrainREV();
    m_liftWheelsBack = new LiftWheelsBack();
    m_liftWheelsFront = new LiftWheelsFront();
    m_rgbController = new RGBController(new CANifier(RobotMap.canifer));
    m_oi = new OI();
    
    m_chooser.setDefaultOption("Default Auto", new DrivetrainArcadeDrive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  @Override
  public void robotPeriodic() {
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
  }

  @Override
  public void testPeriodic() {
  }
}
