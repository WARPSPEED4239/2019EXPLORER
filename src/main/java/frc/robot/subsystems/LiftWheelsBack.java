package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftWheelsBackUp;

public class LiftWheelsBack extends Subsystem {

  private DoubleSolenoid liftWheelsSolenoidBack = new DoubleSolenoid(RobotMap.liftWheelsSolenoidBackForward, RobotMap.liftWheelsSolenoidBackReverse);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftWheelsBackUp());
  }

  public void liftWheelsPistonBackUp() {
    liftWheelsSolenoidBack.set(Value.kReverse);
  }

  public void liftWheelsPistonBackDown() {
    liftWheelsSolenoidBack.set(Value.kForward);
  }
}
