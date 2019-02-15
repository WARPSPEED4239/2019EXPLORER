package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RampPistonRetract;

public class RampPiston extends Subsystem {

  private DoubleSolenoid piston = new DoubleSolenoid(RobotMap.rampSolenoidForward, RobotMap.rampSolenoidReverse);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RampPistonRetract());
  }

  public void pistonExtend() {
    piston.set(Value.kForward);
  }

  public void pistonRetract() {
    piston.set(Value.kReverse);
  }
}
