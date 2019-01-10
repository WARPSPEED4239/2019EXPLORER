package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainArcadeDrive;

public class Drivetrain extends Subsystem {
  private final int PEAK_CURRENT_LIMIT = 45;
	private final int CONTINUOUS_CURRENT_LIMIT = 35;
  private final int PEAK_CURRENT_DURATION_MILLIS = 100;
  
	private final double RAMP_RATE = 0.25;
	private final int TIMEOUT_MILLIS = 10;
  
  private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.drivetrainLeftOne);
  private WPI_TalonSRX leftSlave1 = new WPI_TalonSRX(RobotMap.drivetrainLeftTwo);
  private WPI_TalonSRX leftSlave2 = new WPI_TalonSRX(RobotMap.drivetrainLeftThree);
  private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.drivetrainRightFour);
  private WPI_TalonSRX rightSlave1 = new WPI_TalonSRX(RobotMap.drivetrainRightFive);
  private WPI_TalonSRX rightSlave2 = new WPI_TalonSRX(RobotMap.drivetrainRightSix);

  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public Drivetrain() {
    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);

    setCurrentLimit(leftMaster);
    setCurrentLimit(rightMaster);

    leftMaster.configOpenloopRamp(RAMP_RATE, 0);
    rightMaster.configOpenloopRamp(RAMP_RATE, 0);

    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    leftMaster.setSensorPhase(false);

    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    rightMaster.setSensorPhase(false);

    leftMaster.config_kF(0, 0.0, TIMEOUT_MILLIS);
    leftMaster.config_kP(0, 0.0, TIMEOUT_MILLIS);
    leftMaster.config_kI(0, 0.0, TIMEOUT_MILLIS);
    leftMaster.config_kD(0, 0.0, TIMEOUT_MILLIS);

    rightMaster.config_kF(0, 0.0, TIMEOUT_MILLIS);
    rightMaster.config_kP(0, 0.0, TIMEOUT_MILLIS);
    rightMaster.config_kI(0, 0.0, TIMEOUT_MILLIS);
    rightMaster.config_kD(0, 0.0, TIMEOUT_MILLIS);
  }

  public WPI_TalonSRX getLeftController() {
    return leftMaster;
  }

  public WPI_TalonSRX getRightController() {
    return rightMaster;
  }

  @Override
  public void periodic() {

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DrivetrainArcadeDrive());
  }
  
  public void arcadeDrive(double move, double rotate) {
    final double MIN_MOVE_THRESHOLD = 0.05;
    final double MIN_ROTATE_THRESHOLD = 0.20;

    if (Math.abs(move) < MIN_MOVE_THRESHOLD)
      move = 0.0;

    if (Math.abs(rotate) < MIN_ROTATE_THRESHOLD)
      rotate = 0.0;

    drive.arcadeDrive(move, rotate);
  }

  public void stop() {
    leftMaster.stopMotor();
    rightMaster.stopMotor();
  }
  
  public void setIsAuto (boolean isAuto) {
    if (isAuto) {
      drive.setSafetyEnabled(false);	
      leftMaster.setInverted(true);
      leftSlave1.setInverted(true);
      leftSlave2.setInverted(true);
    }

    else {
      drive.setSafetyEnabled(true);
      leftMaster.setInverted(false);
      leftSlave1.setInverted(false);
      leftSlave2.setInverted(false);
    }
  }
    private void setCurrentLimit(WPI_TalonSRX controller) {
      controller.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
      controller.configPeakCurrentLimit(PEAK_CURRENT_LIMIT);
      controller.configPeakCurrentDuration(PEAK_CURRENT_DURATION_MILLIS);
      controller.enableCurrentLimit(true);
    }
  }
