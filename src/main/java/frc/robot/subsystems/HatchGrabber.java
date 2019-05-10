package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchGrabberRelease;

public class HatchGrabber extends Subsystem {

  private DoubleSolenoid mPiston = new DoubleSolenoid(RobotMap.hatchGrabberSolenoidForward, RobotMap.hatchGrabberSolenoidReverse);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchGrabberRelease());
  }

  public void extend() {
    mPiston.set(Value.kForward);
  }

  public void retract() {
    mPiston.set(Value.kReverse);
  }
}
