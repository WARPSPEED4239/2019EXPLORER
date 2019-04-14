package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.tools.UnitConversion;

public class Elevator extends Subsystem {

  private final WPI_TalonSRX mMaster;
  private final WPI_TalonSRX mSlave;

  private final DigitalInput mBottom;
  private final DigitalInput mTop2To1;
  private final DigitalInput mTop3To2;

  private final int kPeakCurrentLimit = 60;
  private final int kContinuousCurrentLimit = 40;

  private final int kMaxVelocity = 3312; //2000
  private final int kMaxAcceleration = 6624; //2000

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
    mSlave.setNeutralMode(NeutralMode.Brake);

    mMaster.configPeakCurrentLimit(kPeakCurrentLimit);
    mSlave.configPeakCurrentLimit(kPeakCurrentLimit);
    mMaster.configContinuousCurrentLimit(kContinuousCurrentLimit);
    mSlave.configContinuousCurrentLimit(kContinuousCurrentLimit);
    mMaster.enableCurrentLimit(true);
    mSlave.enableCurrentLimit(true);

    mMaster.setInverted(true);
    mMaster.setSensorPhase(false);

    mMaster.configNominalOutputForward(0.0);
    mMaster.configNominalOutputReverse(0.0);
    mMaster.configPeakOutputForward(1.0);
    mMaster.configPeakOutputReverse(-1.0);

    mSlave.configNominalOutputForward(0.0);
    mSlave.configNominalOutputReverse(0.0);
    mSlave.configPeakOutputForward(1.0);
    mSlave.configPeakOutputReverse(-1.0);

    mMaster.selectProfileSlot(kSlotIdx, kPIDIdx);
    mMaster.config_kF(kSlotIdx, 1023.0 / 4000.0);
    mMaster.config_kP(kSlotIdx, 0.1);
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
    setDefaultCommand(new ElevatorSetPercentOutput(0.0));
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

  public double getActiveTrajetoryPositionInInches() {
    double positionInSRXUnits = mMaster.getActiveTrajectoryPosition();
    double positionInRotations = UnitConversion.convertSRXUnitsToRotations(positionInSRXUnits);
    double positionInInches = UnitConversion.convertRotationsToInches(positionInRotations, kDrumDiameter);
    
    return positionInInches;
  }

  public double getActiveTrajectoryAccelerationInInchesPerSecondSquared() {
    double EncoderTicksPerRotation = UnitConversion.convertPositionInInchesToRotations(1.0, kDrumDiameter);
    double EncoderTicksPerInch = UnitConversion.convertRotationsToSRXUnits(EncoderTicksPerRotation);
    double activeTrajectoryAccelerationInInchesPerSecondSquared;

    if (getVelocityInInchesPerSecond() > 5.0) {
      activeTrajectoryAccelerationInInchesPerSecondSquared = kMaxAcceleration * 10.0 / (EncoderTicksPerInch * 386.09); // 10 ~ Gravity, 386.09 = Gravity Constant
    }
    else if (getVelocityInInchesPerSecond() < -5.0) {
      activeTrajectoryAccelerationInInchesPerSecondSquared = -kMaxAcceleration * 10.0 / (EncoderTicksPerInch * 386.09); // 10 ~ Gravity, 386.09 = Gravity Constant
    }
    else {
      activeTrajectoryAccelerationInInchesPerSecondSquared = 0.0;
    }
    return activeTrajectoryAccelerationInInchesPerSecondSquared;
  }

  public void setPercentOutput(double output) {
    if (output < -1) {
      output = -1;
    } else if (output > 1) {
      output = 1;
    }
    mMaster.set(ControlMode.PercentOutput, output);
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

  public void zeroEncoder() {
    mMaster.setSelectedSensorPosition(0);
  }
  
  public void setEncoderValueInInches(double positionInInches) {
    double positionInRotations = UnitConversion.convertPositionInInchesToRotations(positionInInches, kDrumDiameter);
    int positionInSRXUnits = (int) UnitConversion.convertRotationsToSRXUnits(positionInRotations);

    mMaster.setSelectedSensorPosition(positionInSRXUnits);
  }
}
