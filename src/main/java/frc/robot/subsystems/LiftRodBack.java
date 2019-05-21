package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftRodBackRetract;

public class LiftRodBack extends Subsystem {

private DoubleSolenoid mPiston = new DoubleSolenoid(RobotMap.liftRodBackSolenoidForward, RobotMap.liftRodBackSolenoidReverse);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftRodBackRetract());
  }

  public void retract() {
    mPiston.set(Value.kReverse);
  }

  public void extend() {
    mPiston.set(Value.kForward);
  }
}
