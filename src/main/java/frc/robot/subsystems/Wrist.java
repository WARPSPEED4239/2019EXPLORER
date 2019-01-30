package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.WristStop;

public class Wrist extends Subsystem {

  private WPI_TalonSRX mWristMotor;
  
  private DigitalInput mWristLimitSwitch;

  public Wrist (WPI_TalonSRX wristMotor, DigitalInput wristLimitSwitch) {
    mWristMotor = wristMotor;
    mWristLimitSwitch = wristLimitSwitch;

  }

  public static Wrist create() {
    WPI_TalonSRX mWristMotor = new WPI_TalonSRX(RobotMap.wristMotor);
    DigitalInput mWristLimitSwitch = new DigitalInput(RobotMap.wristLimitSwitch);

    return new Wrist(mWristMotor, mWristLimitSwitch);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristStop());
  }

  public void wristMotorStop() {
    updateSmartDashboard();
    mWristMotor.set(0.0);
  }

  public void wristMotorUp() {
    updateSmartDashboard();
    mWristMotor.set(1.0);
  }

  public void wristMotorDown() {
    updateSmartDashboard();
    mWristMotor.set(-1.0);
  }

  public boolean getWristLimitSwitch() {
    updateSmartDashboard();
    return !mWristLimitSwitch.get();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putBoolean("Wrist Limit Switch", mWristLimitSwitch.get());
  }
}
