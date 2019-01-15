package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.WristStop;

public class Wrist extends Subsystem {

  private WPI_TalonSRX wristMotor = new WPI_TalonSRX(RobotMap.wristMotor);
  private AnalogPotentiometer wristPotentiometer = new AnalogPotentiometer(RobotMap.wristPotentiometer, 314, 0);
  //private AnalogInput wristPot = new AnalogInput(RobotMap.wristPotentiometer);

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

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("Pot Reading Degrees?", wristPotentiometer.get());
    //SmartDashboard.putNumber("Volts", wristPot.getVoltage());
    //SmartDashboard.putNumber("Average Volts", wristPot.getAverageVoltage());
  }
}
