package frc.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.GeneralPin;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.WristManualControl;
import frc.robot.tools.Logger;
import frc.robot.tools.UnitConversion;

public class Wrist extends Subsystem {

  private WPI_TalonSRX mMotor;
  //private CANifier mCanifier;

  private final int kPeakCurrentLimit = 35;
  private final int kContinuousCurrentLimit = 30;

  private final int kMaxVelocity = 15000; // CALCULATE THESE VALUES (HALF)
  private final int kMaxAcceleration = 6000; // CALCULATE THESE VALUES (HALF)

  private final int kSlotIdx = 0;
  private final int kPIDIdx = 0;

  private final double kGearRatio = 12.0 / 15.0;

  public Wrist (WPI_TalonSRX wristMotor/*, CANifier canifier*/) {
    mMotor = wristMotor;
    //mCanifier = canifier;

    mMotor.configFactoryDefault();

    mMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

    mMotor.setNeutralMode(NeutralMode.Brake);

    mMotor.configPeakCurrentLimit(kPeakCurrentLimit);
    mMotor.configContinuousCurrentLimit(kContinuousCurrentLimit);

    mMotor.setInverted(false);
    mMotor.setSensorPhase(false);

    //mMotor.configForwardLimitSwitchSource(RemoteLimitSwitchSource.RemoteCANifier, LimitSwitchNormal.NormallyOpen, RobotMap.wristCanifier);
    //mMotor.configReverseLimitSwitchSource(RemoteLimitSwitchSource.RemoteCANifier, LimitSwitchNormal.NormallyOpen, RobotMap.wristCanifier);

    mMotor.configNominalOutputForward(0.0);
    mMotor.configNominalOutputReverse(0.0);
    mMotor.configPeakOutputForward(1.0);
    mMotor.configPeakOutputReverse(-1.0);

    mMotor.selectProfileSlot(kSlotIdx, kPIDIdx);
    mMotor.config_kF(kSlotIdx, 0.0);
    mMotor.config_kP(kSlotIdx, 0.0);
    mMotor.config_kI(kSlotIdx, 0.0);
    mMotor.config_kD(kSlotIdx, 0.0);

    mMotor.configMotionCruiseVelocity(kMaxVelocity);
    mMotor.configMotionAcceleration(kMaxAcceleration);
  }

  public static Wrist create() {
    WPI_TalonSRX mMotor = new WPI_TalonSRX(RobotMap.wristMotor);
    //CANifier mCanifier = new CANifier(RobotMap.wristCanifier);

    return new Wrist(mMotor/*, mCanifier*/);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristManualControl());
  }

  public boolean getTopLimitSwitch() {
    return false;//mCanifier.getGeneralInput(GeneralPin.LIMF);
  }

  public boolean getBottomLimitSwitch() {
    return false;//mCanifier.getGeneralInput(GeneralPin.LIMR);
  }

  public void setPercentOutput(double output) {
    mMotor.set(ControlMode.PercentOutput, output);
    Logger.log(output);
  }

  public void setPositionInDegrees(double positionInDegrees) {
    double positionInSRXUnits = UnitConversion.convertRotationsToSRXUnits(positionInDegrees) / kGearRatio;

    mMotor.set(ControlMode.MotionMagic, positionInSRXUnits);
  }

  public double getPositionInDegrees() {
    double positionInSRXUnits = mMotor.getSelectedSensorPosition();
    double positionInDegrees = UnitConversion.convertSRXUnitsToDegrees(positionInSRXUnits) * kGearRatio;

    return positionInDegrees;
  }

  public double getVelocityInDegreesPerSecond() {
    double velocityInSrxUnitsPerSec = mMotor.getSelectedSensorVelocity() / 10;
    double velocityInDegreesPerSecond = UnitConversion.convertSRXUnitsToDegrees(velocityInSrxUnitsPerSec) * kGearRatio;

    return velocityInDegreesPerSecond;
  }

  public void resetEncoder() {
    mMotor.setSelectedSensorPosition(0);
  }
}
