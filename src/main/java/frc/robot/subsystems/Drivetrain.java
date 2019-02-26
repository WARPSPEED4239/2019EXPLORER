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

  private final int CURRENT_LIMIT = 40;
  private final int PEAK_CURRENT_LIMIT = 45;
  private final int PEAK_CURRENT_DURATION_MILLIS = 100;
  
	private final double RAMP_RATE = 0.2;
  
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
    
    leftMaster.setSmartCurrentLimit(CURRENT_LIMIT);
    rightMaster.setSmartCurrentLimit(CURRENT_LIMIT);
    leftMaster.setSecondaryCurrentLimit(PEAK_CURRENT_LIMIT, PEAK_CURRENT_DURATION_MILLIS);
    rightMaster.setSecondaryCurrentLimit(PEAK_CURRENT_LIMIT, PEAK_CURRENT_DURATION_MILLIS);

<<<<<<< HEAD
<<<<<<< HEAD
    leftMaster.setOpenLoopRampRate(kRampRate);
    rightMaster.setOpenLoopRampRate(kRampRate);
=======
    leftMaster.setRampRate(RAMP_RATE);
    rightMaster.setRampRate(RAMP_RATE);
>>>>>>> parent of 58dad00... Updated Vendors, and added starting config
=======
    leftMaster.setOpenLoopRampRate(RAMP_RATE);
    rightMaster.setOpenLoopRampRate(RAMP_RATE);
>>>>>>> parent of 47c8ac3... Changed IMU to get Roll and changed syntax on constants

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

  public double getIMUYaw() {
    IMU.getYawPitchRoll(ypr);
<<<<<<< HEAD
    return ypr[2];
=======
    return ypr[0];
>>>>>>> parent of 47c8ac3... Changed IMU to get Roll and changed syntax on constants
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
