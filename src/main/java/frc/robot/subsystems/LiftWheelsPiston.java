package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftWheelsPistonUp;

public class LiftWheelsPiston extends Subsystem {

  private DoubleSolenoid mPiston = new DoubleSolenoid(RobotMap.liftWheelsSolenoidForward, RobotMap.liftWheelsSolenoidReverse);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftWheelsPistonUp());
  }

  public void retract() {
    mPiston.set(Value.kReverse);
  }

  public void extend() {
    mPiston.set(Value.kForward);
  }
}
