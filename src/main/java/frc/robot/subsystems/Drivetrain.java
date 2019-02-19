package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainArcadeDrive;

public class Drivetrain extends Subsystem {
  private double [] ypr = new double[3];

  private final int kCurrentLimit = 40;
  private final int kPeakCurrentLimit = 45;
  private final int kPeakCurrentDurationMills = 100;
  
	private final double kRampRate = 0.2;
  
  private double leftEncoderBase;
  private double rightEncoderBase;
  
  private CANSparkMax leftMaster = new CANSparkMax(RobotMap.drivetrainLeftOne, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave1 = new CANSparkMax(RobotMap.drivetrainLeftTwo, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave2 = new CANSparkMax(RobotMap.drivetrainLeftThree, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightMaster = new CANSparkMax(RobotMap.drivetrainRightFour, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave1 = new CANSparkMax(RobotMap.drivetrainRightFive, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave2 = new CANSparkMax(RobotMap.drivetrainRightSix, CANSparkMaxLowLevel.MotorType.kBrushless);

  private PigeonIMU IMU = new PigeonIMU(RobotMap.pigeonIMU);

  private CANEncoder leftEncoder = new CANEncoder(leftMaster);
  private CANEncoder rightEncoder = new CANEncoder(rightMaster);

  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public Drivetrain() {
    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);
    
    leftMaster.setSmartCurrentLimit(kCurrentLimit);
    rightMaster.setSmartCurrentLimit(kCurrentLimit);
    leftMaster.setSecondaryCurrentLimit(kPeakCurrentLimit, kPeakCurrentDurationMills);
    rightMaster.setSecondaryCurrentLimit(kPeakCurrentLimit, kPeakCurrentDurationMills);

    leftMaster.setOpenLoopRampRate(kRampRate);
    rightMaster.setOpenLoopRampRate(kRampRate);

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

  @Override
  public void periodic() {
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DrivetrainArcadeDrive());
  }
  
  public void arcadeDrive(double move, double rotate) {
    final double MIN_MOVE_THRESHOLD = 0.05;
    final double MIN_ROTATE_THRESHOLD = 0.05;

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
    double revs = -(leftEncoder.getPosition() - leftEncoderBase);
    return convertPosition(revs);
  }

  public double getRightEncoderPosition() {
     double revs = rightEncoder.getPosition() - rightEncoderBase;
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

  public double getIMURoll() {
    IMU.getYawPitchRoll(ypr);
    return ypr[2];
  }

  public void resetSensors() {
    leftEncoderBase = leftEncoder.getPosition();
    rightEncoderBase = rightEncoder.getPosition();
    IMU.setYaw(0.0);
  }

  public double convertPosition(double input) {
    return 2.0 * Math.PI * 0.25 * input / 7.08; 
  }

  public double convertVelocity(double input) {
    return 2.0 * Math.PI * 0.25 * input / 7.08 / 60; 
  }
}
