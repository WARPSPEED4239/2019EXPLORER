package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainArcadeDrive;

public class Drivetrain extends Subsystem {
  private final int CURRENT_LIMIT = 40;
  private final int PEAK_CURRENT_LIMIT = 45;
  private final int PEAK_CURRENT_DURATION_MILLIS = 100;
  
	private final double RAMP_RATE = 0.2;
	private final int TIMEOUT_MILLIS = 10;
  
  private CANSparkMax leftMaster = new CANSparkMax(RobotMap.drivetrainLeftOne, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave1 = new CANSparkMax(RobotMap.drivetrainLeftTwo, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax leftSlave2 = new CANSparkMax(RobotMap.drivetrainLeftThree, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightMaster = new CANSparkMax(RobotMap.drivetrainRightFour, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave1 = new CANSparkMax(RobotMap.drivetrainRightFive, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax rightSlave2 = new CANSparkMax(RobotMap.drivetrainRightSix, CANSparkMaxLowLevel.MotorType.kBrushless);

  private CANPIDController leftController = new CANPIDController(leftMaster);
  private CANPIDController rightController = new CANPIDController(rightMaster);

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

    leftMaster.setRampRate(RAMP_RATE);
    rightMaster.setRampRate(RAMP_RATE);
    
    leftController.setFF(0.0, 0);
    leftController.setP(0.0, 0);
    leftController.setI(0.0, 0);
    leftController.setD(0.0, 0);

    rightController.setFF(0.0, 0);
    rightController.setP(0.0, 0);
    rightController.setI(0.0, 0);
    rightController.setD(0.0, 0);
  }

  public CANSparkMax getLeftController() {
    return leftMaster;
  }

  public CANSparkMax getRightController() {
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
}
