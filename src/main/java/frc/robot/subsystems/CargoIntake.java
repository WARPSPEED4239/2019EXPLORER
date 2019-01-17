package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoIntakeStop;

public class CargoIntake extends Subsystem {

  private WPI_VictorSPX cargoIntakeMotor = new WPI_VictorSPX(RobotMap.cargoIntakeMotor);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoIntakeStop());
  }

  public void cargoIntakeStop() {
    cargoIntakeMotor.set(0.0);
  }

  public void cargoIntakeIn() {
    cargoIntakeMotor.set(-0.5);
  }

  public void cargoIntakeOut () {
    cargoIntakeMotor.set(0.5);
  }
}
