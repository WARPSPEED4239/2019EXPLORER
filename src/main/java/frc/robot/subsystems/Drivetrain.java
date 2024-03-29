package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainArcadeDrive;

public class Drivetrain extends Subsystem {
  private final int kCurrentLimit = 40;

  private final double kRampRate = 0.2;

  private CANSparkMax leftMaster = new CANSparkMax(RobotMap.drivetrainLeftOne, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave1 = new CANSparkMax(RobotMap.drivetrainLeftTwo, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave2 = new CANSparkMax(RobotMap.drivetrainLeftThree, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightMaster = new CANSparkMax(RobotMap.drivetrainRightFour, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave1 = new CANSparkMax(RobotMap.drivetrainRightFive, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave2 = new CANSparkMax(RobotMap.drivetrainRightSix, CANSparkMaxLowLevel.MotorType.kBrushless);

  private CANEncoder leftEncoder = new CANEncoder(leftMaster);
  private CANEncoder rightEncoder = new CANEncoder(rightMaster);

  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);
  
  private NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  public Drivetrain() {
    leftMaster.restoreFactoryDefaults();
    leftSlave1.restoreFactoryDefaults();
    leftSlave2.restoreFactoryDefaults();
    rightMaster.restoreFactoryDefaults();
    rightSlave1.restoreFactoryDefaults();
    rightSlave2.restoreFactoryDefaults();

    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);
    
    leftMaster.setIdleMode(IdleMode.kBrake);
    leftSlave1.setIdleMode(IdleMode.kBrake);
    leftSlave2.setIdleMode(IdleMode.kBrake);
    rightMaster.setIdleMode(IdleMode.kBrake);
    rightSlave1.setIdleMode(IdleMode.kBrake);
    rightSlave2.setIdleMode(IdleMode.kBrake);

    leftMaster.setSmartCurrentLimit(kCurrentLimit);
    leftSlave1.setSmartCurrentLimit(kCurrentLimit);
    leftSlave2.setSmartCurrentLimit(kCurrentLimit);
    rightMaster.setSmartCurrentLimit(kCurrentLimit);
    rightSlave1.setSmartCurrentLimit(kCurrentLimit);
    rightSlave2.setSmartCurrentLimit(kCurrentLimit);

    leftMaster.setOpenLoopRampRate(kRampRate);
    leftSlave1.setOpenLoopRampRate(kRampRate);
    leftSlave2.setOpenLoopRampRate(kRampRate);
    rightMaster.setOpenLoopRampRate(kRampRate);
    rightSlave1.setOpenLoopRampRate(kRampRate);
    rightSlave2.setOpenLoopRampRate(kRampRate);

    leftMaster.burnFlash();
    leftSlave1.burnFlash();
    leftSlave2.burnFlash();
    rightMaster.burnFlash();
    rightSlave1.burnFlash();
    rightSlave2.burnFlash();
    
    resetSensors();
  }

  public CANSparkMax getLeftController() {
    return leftMaster;
  }

  public CANSparkMax getRightController() {
    return rightMaster;
  }

  public CANEncoder getLeftEncoder() {
    return leftEncoder;
  }

  public CANEncoder getRightEncoder() {
    return rightEncoder;
  }

  public NetworkTable getLimelightTable() {
    return limelightTable;
  }

  @Override
  public void periodic() {
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DrivetrainArcadeDrive());
  }
  
  public void arcadeDrive(double move, double rotate) {
    final double MIN_MOVE_THRESHOLD = 0.07;
    final double MIN_ROTATE_THRESHOLD = 0.07;

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

  public double getLeftEncoderPosition() {
    double revs = -(leftEncoder.getPosition());
    return convertPosition(revs);
  }

  public double getRightEncoderPosition() {
     double revs = rightEncoder.getPosition();
     return convertPosition(revs);
  }

  public double getLeftEncoderVelocity() {
     double input = -leftEncoder.getVelocity();
     return convertVelocity(input);
  }

  public double getRightEncoderVelocity() {
    double input = rightEncoder.getVelocity();
    return convertVelocity(input);
  }

  public void resetSensors() {
    leftEncoder.setPosition(0.0);
    rightEncoder.setPosition(0.0);
  }

  public double convertPosition(double input) {
    return 2.0 * Math.PI * 0.25 * input / 7.08; 
  }

  public double convertVelocity(double input) {
    return 2.0 * Math.PI * 0.25 * input / 7.08 / 60; 
  }

  public void driveWithVisionAssist(double move, double rotate) {
    drive.arcadeDrive(move, rotate, false);
  }
}
