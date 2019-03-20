package frc.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.GeneralPin;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.WristSetPercentOutput;
import frc.robot.tools.UnitConversion;

public class Wrist extends Subsystem {

  private WPI_TalonSRX mMotor;
  private DoubleSolenoid mSolenoid;
  private CANifier mCanifier;

  private final int kPeakCurrentLimit = 35;
  private final int kContinuousCurrentLimit = 30;

  private final int kMaxVelocity = 394; //275
  private final int kMaxAcceleration = 788; //TUNE? //275

  private final int kSlotIdx = 0;
  private final int kPIDIdx = 0;

  public Wrist (WPI_TalonSRX motor, DoubleSolenoid solenoid, CANifier canifier) {
    mMotor = motor;
    mSolenoid = solenoid;
    mCanifier = canifier;

    mMotor.configFactoryDefault();

    mMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

    mMotor.setNeutralMode(NeutralMode.Brake);

    mMotor.configPeakCurrentLimit(kPeakCurrentLimit);
    mMotor.configContinuousCurrentLimit(kContinuousCurrentLimit);

    mMotor.setInverted(true);
    mMotor.setSensorPhase(false);

    mMotor.configForwardLimitSwitchSource(RemoteLimitSwitchSource.RemoteCANifier, LimitSwitchNormal.NormallyOpen, RobotMap.canifier);
    mMotor.configReverseLimitSwitchSource(RemoteLimitSwitchSource.RemoteCANifier, LimitSwitchNormal.NormallyOpen, RobotMap.canifier);

    mMotor.configNominalOutputForward(0.0);
    mMotor.configNominalOutputReverse(0.0);
    mMotor.configPeakOutputForward(1.0);
    mMotor.configPeakOutputReverse(-1.0);

    mMotor.selectProfileSlot(kSlotIdx, kPIDIdx);
    mMotor.config_IntegralZone(kSlotIdx, 50);
    mMotor.config_kF(kSlotIdx, 1023.0 / 550.0);
    mMotor.config_kP(kSlotIdx, 5.0);
    mMotor.config_kI(kSlotIdx, 0.0);
    mMotor.config_kD(kSlotIdx, 50.0);

    mMotor.configMotionCruiseVelocity(kMaxVelocity);
    mMotor.configMotionAcceleration(kMaxAcceleration);
  }

  public static Wrist create() {
    WPI_TalonSRX mMotor = new WPI_TalonSRX(RobotMap.wristMotor);
    DoubleSolenoid mSolenoid = new DoubleSolenoid(RobotMap.wristLockSolenoidForward, RobotMap.wristLockSolenoidReverse);
    CANifier mCanifier = new CANifier(RobotMap.canifier);

    return new Wrist(mMotor, mSolenoid, mCanifier);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristSetPercentOutput(0.0));
  }

  public boolean getTopLimitSwitch() {
    return !mCanifier.getGeneralInput(GeneralPin.LIMF);
  }

  public boolean getBottomLimitSwitch() {
    return !mCanifier.getGeneralInput(GeneralPin.LIMR);
  }

  public void setPercentOutput(double output) {
    if (output < -1) {
      output = -1;
    } else if (output > 1) {
      output = 1;
    }
    mMotor.set(ControlMode.PercentOutput, output);
  }

  public void setPositionInDegrees(double positionInDegrees, double arbFeedForward) {
    double positionInSRXUnits = UnitConversion.convertPositionInDegreesToSRXUnits(positionInDegrees);

    mMotor.set(ControlMode.MotionMagic, positionInSRXUnits, DemandType.ArbitraryFeedForward, arbFeedForward);
  }

  public double getPositionInDegrees() {
    double positionInSRXUnits = mMotor.getSelectedSensorPosition();
    double positionInDegrees = UnitConversion.convertSRXUnitsToDegrees(positionInSRXUnits);

    return positionInDegrees;
  }

  public double getActiveTrajectoryPositionInDegrees() {
    double positionInSRXUnits = mMotor.getActiveTrajectoryPosition();
    double positionInDegrees = UnitConversion.convertSRXUnitsToDegrees(positionInSRXUnits);

    return positionInDegrees;
  }

  public double getActiveTrajectoryAccelerationInDegreesPerSecondSquared() { //TODO Figure out this math
    double EncoderTicksPerDegree = UnitConversion.convertPositionInDegreesToSRXUnits(1.0);

    return kMaxAcceleration * 10 / (EncoderTicksPerDegree * 386.09);
  }

  public double getVelocityInDegreesPerSecond() {
    double velocityInSrxUnitsPerSec = mMotor.getSelectedSensorVelocity() / 10;
    double velocityInDegreesPerSecond = UnitConversion.convertSRXUnitsToDegrees(velocityInSrxUnitsPerSec);

    return velocityInDegreesPerSecond;
  }

  public void zeroEncoder() {
    mMotor.setSelectedSensorPosition(0);
  }

  public void setEncoderValueInDegrees(double positionInDegrees) {
    int positionInSRXUnits = (int) UnitConversion.convertPositionInDegreesToSRXUnits(positionInDegrees);
    
    mMotor.setSelectedSensorPosition(positionInSRXUnits);
  }

  public void lockExtend() {
    mSolenoid.set(Value.kForward);
  }

  public void lockRetract() {
    mSolenoid.set(Value.kReverse);
  }
}
