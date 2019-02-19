package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftWheelsMotorStop;

public class LiftWheelsMotor extends Subsystem {

  private WPI_TalonSRX mMotor = new WPI_TalonSRX(RobotMap.liftWheelsMotor);

  public LiftWheelsMotor() {
    mMotor.configFactoryDefault();

    mMotor.setInverted(false);
    mMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftWheelsMotorStop());
  }

  public void stop() {
    mMotor.set(0.0);
  }

  public void forward() {
    mMotor.set(0.2);
  }

  public void reverse() {
    mMotor.set(-0.2);
  }
}
