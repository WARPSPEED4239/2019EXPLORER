package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoIntakeStop;

public class CargoIntake extends Subsystem {

  private WPI_VictorSPX mMotor = new WPI_VictorSPX(RobotMap.cargoIntakeMotor);

  public CargoIntake() {
    mMotor.configFactoryDefault();

    mMotor.setInverted(false);
    mMotor.setNeutralMode(NeutralMode.Brake);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoIntakeStop());
  }

  public void stop() {
    mMotor.set(0.0);
  }

  public void in() {
    mMotor.set(-0.7);
  }

  public void out () {
    mMotor.set(1.0);
  }
}
