package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainArcadeDrive;

public class Drivetrain extends Subsystem {
  /*private double [] ypr = new double[3];

  private final int CURRENT_LIMIT = 40;
  private final int PEAK_CURRENT_LIMIT = 45;
  private final int PEAK_CURRENT_DURATION_MILLIS = 100;
  
	private final double RAMP_RATE = 0.2;
	private final int TIMEOUT_MILLIS = 10;
  */
  private CANSparkMax leftMaster = new CANSparkMax(RobotMap.drivetrainLeftOne, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave1 = new CANSparkMax(RobotMap.drivetrainLeftTwo, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave2 = new CANSparkMax(RobotMap.drivetrainLeftThree, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightMaster = new CANSparkMax(RobotMap.drivetrainRightFour, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave1 = new CANSparkMax(RobotMap.drivetrainRightFive, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave2 = new CANSparkMax(RobotMap.drivetrainRightSix, CANSparkMaxLowLevel.MotorType.kBrushless);

  //private PigeonIMU IMU = new PigeonIMU(RobotMap.pigeonIMU);

  //private CANEncoder leftEncoder = new CANEncoder(leftMaster);
  //private CANEncoder rightEncoder = new CANEncoder(rightMaster);
  
  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public Drivetrain() {
    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);
    
    /*leftMaster.setSmartCurrentLimit(CURRENT_LIMIT);
    rightMaster.setSmartCurrentLimit(CURRENT_LIMIT);
    leftMaster.setSecondaryCurrentLimit(PEAK_CURRENT_LIMIT, PEAK_CURRENT_DURATION_MILLIS);
    rightMaster.setSecondaryCurrentLimit(PEAK_CURRENT_LIMIT, PEAK_CURRENT_DURATION_MILLIS);

    leftMaster.setRampRate(RAMP_RATE);
    rightMaster.setRampRate(RAMP_RATE);*/
  }

  /*public CANSparkMax getLeftController() {
    return leftMaster;
  }

  public CANSparkMax getRightController() {
    return rightMaster;
  }*/

  /*public CANEncoder getLeftEncoder() {
    return leftEncoder;
  }

  public CANEncoder getRightEncoder() {
    return rightEncoder;
  }*/

  /*@Override
  public void periodic() {
  }*/

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DrivetrainArcadeDrive());
  }
  
  public void arcadeDrive(double move, double rotate) {
    /*final double MIN_MOVE_THRESHOLD = 0.05;
    final double MIN_ROTATE_THRESHOLD = 0.05;

    if (Math.abs(move) < MIN_MOVE_THRESHOLD)
      move = 0.0;

    if (Math.abs(rotate) < MIN_ROTATE_THRESHOLD)
      rotate = 0.0;
    */
    drive.arcadeDrive(move, rotate);
  }

  /*public void stop() {
    leftMaster.stopMotor();
    rightMaster.stopMotor();
  }*/

  /*public double getLeftEncoderPosition() {
    return leftEncoder.getPosition();
  }

  public double getRightEncoderPosition() {
    return rightEncoder.getPosition();
  }

  public double getEncoderLeftVelocity() {
    return leftEncoder.getVelocity();
  }

  public double getEncoderRightVelocity() {
    return rightEncoder.getVelocity();
  }*/

  /*public void getIMUdata() {
    IMU.getYawPitchRoll(ypr);
    SmartDashboard.putNumberArray("Pigeon IMU", ypr);
  }*/
}
