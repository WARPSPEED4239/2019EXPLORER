package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoIntakeStop;

public class CargoIntake extends Subsystem {

  private WPI_VictorSPX cargoIntakeBottomMotor = new WPI_VictorSPX(RobotMap.cargoIntakeMotor);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoIntakeStop());
  }

  public void cargoIntakeStop() {
    cargoIntakeBottomMotor.set(0.0);
    cargoIntakeBottomMotor.set(0.0);
  }

  public void cargoIntakeIn() {
    cargoIntakeTopMotor.set(0.5);
    cargoIntakeBottomMotor.set(-0.5);
  }

  public void cargoIntakeOut () {
    cargoIntakeTopMotor.set(-0.5);
    cargoIntakeBottomMotor.set(0.5);
  }
}
