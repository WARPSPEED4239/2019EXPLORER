package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftWheelUp;

public class LiftWheel extends Subsystem {

  private DoubleSolenoid mPiston = new DoubleSolenoid(RobotMap.liftWheelSolenoidForward, RobotMap.liftWheelSolenoidReverse);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftWheelUp());
  }

  public void down() {
    mPiston.set(Value.kReverse);
  }

  public void up() {
    mPiston.set(Value.kForward);
  }
}
