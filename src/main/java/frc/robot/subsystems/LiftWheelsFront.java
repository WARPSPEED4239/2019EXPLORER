package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftWheelsFrontUp;

public class LiftWheelsFront extends Subsystem {

  private DoubleSolenoid liftWheelsSolenoidFront = new DoubleSolenoid(RobotMap.liftWheelsSolenoidFrontForward, RobotMap.liftWheelsSolenoidFrontReverse);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftWheelsFrontUp());
  }

  public void liftWheelsPistonFrontUp() {
    liftWheelsSolenoidFront.set(Value.kReverse);
  }

  public void liftWheelsPistonFrontDown() {
    liftWheelsSolenoidFront.set(Value.kForward);
  }
}
