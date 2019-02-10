package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorManualControl;
import frc.robot.tools.UnitConversion;

public class Elevator extends Subsystem {

  private final WPI_TalonSRX mMaster;
  private final WPI_TalonSRX mSlave;

  private final DigitalInput mLimitSwitch;

  private final int kPeakCurrentLimit = 45;
  private final int kContinuousCurrentLimit = 40;

  private final int kMaxVelocity = 15000; // CALCULATE THESE VALUES (HALF)
  private final int kMaxAcceleration = 6000; // CALCULATE THESE VALUES (HALF)

  private final int kSlotIdx = 0;
  private final int kPIDIdx = 0;

  private final double kDrumDiameter = 1.65;
  private final double kGearRatio = 12.0 / 15.0; //may not have a sprocket reduction

  public Elevator(WPI_TalonSRX master, WPI_TalonSRX slave, DigitalInput limitSwitch) {
    mMaster = master;
    mSlave = slave;
    mLimitSwitch = limitSwitch;

    mMaster.configFactoryDefault();
    mSlave.configFactoryDefault();

    mSlave.follow(mMaster);

    mMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  
    mMaster.setNeutralMode(NeutralMode.Brake);

    mMaster.configPeakCurrentLimit(kPeakCurrentLimit);
    mMaster.configContinuousCurrentLimit(kContinuousCurrentLimit);

    mMaster.setInverted(false); //SET THIS LATER
    mMaster.setSensorPhase(false); //SET THIS LATER

    mMaster.configNominalOutputForward(0.0);
    mMaster.configNominalOutputReverse(0.0);
    mMaster.configPeakOutputForward(1.0);
    mMaster.configPeakOutputReverse(-1.0);

    mMaster.selectProfileSlot(kSlotIdx, kPIDIdx);
    mMaster.config_kF(kSlotIdx, 0.0);
    mMaster.config_kP(kSlotIdx, 0.0);
    mMaster.config_kI(kSlotIdx, 0.0);
    mMaster.config_kD(kSlotIdx, 0.0);

    mMaster.configMotionCruiseVelocity(kMaxVelocity);
    mMaster.configMotionAcceleration(kMaxAcceleration);

    mMaster.setSelectedSensorPosition(0);
  }

  public static Elevator create() {
    WPI_TalonSRX liftMaster = new WPI_TalonSRX(RobotMap.elevatorMotorOne);
    WPI_TalonSRX liftSlave = new WPI_TalonSRX(RobotMap.elevatorMotorTwo);

    DigitalInput liftLimitSwitch = new DigitalInput(RobotMap.elevatorLimitSwitch);

    return new Elevator(liftMaster, liftSlave, liftLimitSwitch);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorManualControl());
  }

  public boolean getLimitSwitch() {
    return !mLimitSwitch.get();
  }

  public void setPercentOutput(double output) {
    mMaster.set(ControlMode.PercentOutput, output);
  }

  public void setPosition(double positionInInches) {
    double positionInRotations = UnitConversion.convertPositionInInchesToRotations(positionInInches, kDrumDiameter) / kGearRatio;
    double positionInSRXUnits = UnitConversion.convertRotationsToSRXUnits(positionInRotations);

    mMaster.set(ControlMode.MotionMagic, positionInSRXUnits);
  }

  public double getPositionInInches() {
    double positionInSRXUnits = mMaster.getSelectedSensorPosition();
    double positionInRotations = UnitConversion.convertSRXUnitsToRotations(positionInSRXUnits) * kGearRatio;
    double positionInInches = UnitConversion.convertRotationsToPositionInInches(positionInRotations, kDrumDiameter);
    
    return positionInInches;
  }

  public double getVelocityInchesPerSecond() {
    double velocityInSRXUnitsPerSec = mMaster.getSelectedSensorVelocity() / 10;
    double velocityInRotationsPerSec = UnitConversion.convertSRXUnitsToRotations(velocityInSRXUnitsPerSec) * kGearRatio;
    double velocityInInchesPerSec = UnitConversion.convertRotationsToPositionInInches(velocityInRotationsPerSec, kDrumDiameter);

    return velocityInInchesPerSec;
  }

  public void resetEncoder() {
    mMaster.setSelectedSensorPosition(0);
  }
}
