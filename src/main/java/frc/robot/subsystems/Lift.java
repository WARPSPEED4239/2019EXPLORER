package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.automated.LiftOperation;

public class Lift extends Subsystem {

  private WPI_TalonSRX mLiftMaster;
  private WPI_TalonSRX mLiftSlave;

  private DigitalInput mLiftLimitSwitch;

  private final int PEAK_CURRENT_LIMIT = 45;
  private final int CONTINUOUS_CURRENT_LIMIT = 40;
  private final int TIMEOUT_MILLIS = 10;

  private final int MAX_VELOCITY = 15000; // CALCULATE THESE VALUES (HALF)
  private final int MAX_ACELLERATION = 6000; // CALCULATE THESE VALUES (HALF)

  private double currentPosition;  //TODO Do the math on how many ticks per inch of lift movement (4096 ticks per rev)

  private boolean isZeroed = false;

  public Lift(WPI_TalonSRX liftMaster, WPI_TalonSRX liftSlave, DigitalInput liftLimitSwitch) {
    mLiftMaster = liftMaster;
    mLiftSlave = liftSlave;
    mLiftLimitSwitch = liftLimitSwitch;

    mLiftSlave.follow(mLiftMaster);

    mLiftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, TIMEOUT_MILLIS);
    mLiftMaster.configPeakCurrentLimit(PEAK_CURRENT_LIMIT, TIMEOUT_MILLIS);
    mLiftMaster.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT, TIMEOUT_MILLIS);

    // mLiftSlave.setSensorPhase(true);
    // mLiftMaster.setInverted(true);

    mLiftMaster.config_kF(0, 0.0, TIMEOUT_MILLIS); // THIS ONE FIRST
    mLiftMaster.config_kP(0, 0.0, TIMEOUT_MILLIS);
    mLiftMaster.config_kI(0, 0.0, TIMEOUT_MILLIS);
    mLiftMaster.config_kD(0, 0.0, TIMEOUT_MILLIS);

    mLiftMaster.configMotionCruiseVelocity(MAX_VELOCITY, TIMEOUT_MILLIS);
    mLiftMaster.configMotionAcceleration(MAX_ACELLERATION, TIMEOUT_MILLIS);

    mLiftMaster.setNeutralMode(NeutralMode.Brake);
  }

  public static Lift create() {
    WPI_TalonSRX liftMaster = new WPI_TalonSRX(RobotMap.liftMotor1);
    WPI_TalonSRX liftSlave = new WPI_TalonSRX(RobotMap.liftMotor2);

    DigitalInput liftLimitSwitch = new DigitalInput(RobotMap.liftLimitSwitch);

    return new Lift(liftMaster, liftSlave, liftLimitSwitch);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftOperation());
  }

  public void liftStop() {
    updateSmartDashboard();
    mLiftMaster.set(0.0);
  }

  public void liftUp() {
    updateSmartDashboard();
    mLiftMaster.set(1.0);
  }

  public void liftDown() {
    updateSmartDashboard();
    mLiftMaster.set(-1.0);
  }

  public boolean getLiftLimitSwitch() {
    return !mLiftLimitSwitch.get();
  }

  public double getLiftCurrentPosition() {
    currentPosition = mLiftMaster.getSelectedSensorPosition();
    return currentPosition;
  }

  public boolean getLiftIsZeroed() {
    return isZeroed;
  }

  private void updateSmartDashboard() {
    SmartDashboard.putBoolean("Lift Limit Switch", getLiftLimitSwitch());
    SmartDashboard.putBoolean("Lift Zeroed", getLiftIsZeroed());
  }

  public void zeroLiftPosition() {
    updateSmartDashboard();
    mLiftMaster.set(ControlMode.PercentOutput, -0.2); 
  }
  
  public void zeroLiftPositionSensor() {
    isZeroed = true;
    mLiftMaster.setSelectedSensorPosition(0, 0, TIMEOUT_MILLIS);
  }

  public void liftMoveTargetToPosition(double targetPosition) { //TODO Have targetPosition actually be set via the state and button press while not breaking itself
    updateSmartDashboard();
    mLiftMaster.set(ControlMode.MotionMagic, targetPosition);
  }

  switch (liftOperationState) {
    case value:
      
      break;
  
    default:
      break;
  }
}
