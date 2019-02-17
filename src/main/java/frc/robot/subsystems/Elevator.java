package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorManualControl;
import frc.robot.tools.Logger;
import frc.robot.tools.UnitConversion;

public class Elevator extends Subsystem {

  private final WPI_TalonSRX mMaster;
  private final WPI_TalonSRX mSlave;

  private final DigitalInput mBottom;
  private final DigitalInput mTop2To1;
  private final DigitalInput mTop3To2;

  private final int kPeakCurrentLimit = 45;
  private final int kContinuousCurrentLimit = 40;

  private final int kMaxVelocity = 15000; // CALCULATE THESE VALUES (HALF)
  private final int kMaxAcceleration = 6000; // CALCULATE THESE VALUES (HALF)

  private final int kSlotIdx = 0;
  private final int kPIDIdx = 0;

  private final double kDrumDiameter = 1.65;

  public Elevator(WPI_TalonSRX master, WPI_TalonSRX slave, DigitalInput bottom, DigitalInput top2To1, DigitalInput top3To2) {
    mMaster = master;
    mSlave = slave;

    mBottom = bottom;
    mTop2To1 = top2To1;
    mTop3To2 = top3To2;

    mMaster.configFactoryDefault();
    mSlave.configFactoryDefault();

    mSlave.follow(mMaster);

    mMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  
    mMaster.setNeutralMode(NeutralMode.Brake);

    mMaster.configPeakCurrentLimit(kPeakCurrentLimit);
    mMaster.configContinuousCurrentLimit(kContinuousCurrentLimit);

    mMaster.setInverted(false);
    mMaster.setSensorPhase(true);

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
  }

  public static Elevator create() {
    WPI_TalonSRX master = new WPI_TalonSRX(RobotMap.elevatorMotorOne);
    WPI_TalonSRX slave = new WPI_TalonSRX(RobotMap.elevatorMotorTwo);

    DigitalInput bottom = new DigitalInput(RobotMap.liftBottomLimitSwitch);
    DigitalInput top2To1 = new DigitalInput(RobotMap.liftTop2To1LimitSwitch);
    DigitalInput top3To2 = new DigitalInput(RobotMap.liftTop3To2LimitSwitch);

    return new Elevator(master, slave, bottom, top2To1, top3To2);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorManualControl(0.0));
  }

  public double getMotorOutputVoltage() {
    return mMaster.getMotorOutputVoltage();
  }

  public boolean getTop3To2LimitSwitch() {
    return !mTop3To2.get();
  }

  public boolean getTop2To1LimitSwitch() {
    return !mTop2To1.get();
  }

  public boolean getBottomLimitSwitch() {
    return !mBottom.get();
  }

  public void setPercentOutput(double output) {
    mMaster.set(ControlMode.PercentOutput, output);
    Logger.log(output);
  }

  public void setPosition(double positionInInches) {
    double positionInRotations = UnitConversion.convertPositionInInchesToRotations(positionInInches, kDrumDiameter);
    double positionInSRXUnits = UnitConversion.convertRotationsToSRXUnits(positionInRotations);

    mMaster.set(ControlMode.MotionMagic, positionInSRXUnits);
  }

  public double getPositionInInches() {
    double positionInSRXUnits = mMaster.getSelectedSensorPosition();
    double positionInRotations = UnitConversion.convertSRXUnitsToRotations(positionInSRXUnits);
    double positionInInches = UnitConversion.convertRotationsToInches(positionInRotations, kDrumDiameter);
    
    return positionInInches;
  }

  public double getVelocityInInchesPerSecond() {
    double velocityInSRXUnitsPerSec = mMaster.getSelectedSensorVelocity() / 10;
    double velocityInRotationsPerSec = UnitConversion.convertSRXUnitsToRotations(velocityInSRXUnitsPerSec);
    double velocityInInchesPerSec = UnitConversion.convertRotationsToInches(velocityInRotationsPerSec, kDrumDiameter);

    return velocityInInchesPerSec;
  }

  public void resetEncoder() {
    mMaster.setSelectedSensorPosition(0);
  }
}
