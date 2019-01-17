package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.WristStop;

public class Wrist extends Subsystem {

  private WPI_TalonSRX wristMotor = new WPI_TalonSRX(RobotMap.wristMotor);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristStop());
  }

  public void wristMotorStop() {
    wristMotor.set(0.0);
  }

  public void wristMotorUp() {
    wristMotor.set(1.0);
  }

  public void wristMotorDown() {
    wristMotor.set(-1.0);
  }
}
