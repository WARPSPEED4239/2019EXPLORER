package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftWheelUp;

public class LiftWheel extends Subsystem {

  private DoubleSolenoid liftWheelsSolenoidFront = new DoubleSolenoid(RobotMap.liftWheelSolenoidForward, RobotMap.liftWheelSolenoidReverse);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftWheelUp());
  }

  public void liftWheelPistonDown() {
    liftWheelsSolenoidFront.set(Value.kReverse);
  }

  public void liftWheelPistionUp() {
    liftWheelsSolenoidFront.set(Value.kForward);
  }
}
