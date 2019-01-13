package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchGrabberExtend;

public class HatchGrabber extends Subsystem {

  private DoubleSolenoid hatchGrabberPiston = new DoubleSolenoid(RobotMap.hatchGrabberSolenoidForward, RobotMap.hatchGrabberSolenoidReverse);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchGrabberExtend());
  }

  public void hatchGrabberExtend() {
    hatchGrabberPiston.set(Value.kForward);
  }

  public void hatchGrabberRetract() {
    hatchGrabberPiston.set(Value.kReverse);
  }
}
