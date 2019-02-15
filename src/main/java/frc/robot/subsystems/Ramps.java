package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RampsStop;

public class Ramps extends Subsystem {

  private WPI_TalonSRX motor = new WPI_TalonSRX(RobotMap.rampMotor);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RampsStop());
  }

  public void rampsStop() {
    motor.set(0.0);
  }

  public void rampsDown() {
    motor.set(-1.0);
  }

  public void rampsUp() {
    motor.set(1.0);
  }
}
