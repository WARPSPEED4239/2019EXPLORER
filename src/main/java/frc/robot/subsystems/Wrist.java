package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.WristStop;

public class Wrist extends Subsystem {

  private WPI_TalonSRX wristMotor = new WPI_TalonSRX(RobotMap.wristMotor);
  
  private DigitalInput wristLimitSwitch = new DigitalInput(RobotMap.wristLimitSwitch);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristStop());
  }

  public void wristMotorStop() {
    updateSmartDashboard();
    wristMotor.set(0.0);
  }

  public void wristMotorUp() {
    updateSmartDashboard();
    wristMotor.set(1.0);
  }

  public void wristMotorDown() {
    updateSmartDashboard();
    wristMotor.set(-1.0);
  }

  public boolean getWristLimitSwitch() {
    updateSmartDashboard();
    return !wristLimitSwitch.get();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putBoolean("Wrist Limit Switch", wristLimitSwitch.get());
  }
}
