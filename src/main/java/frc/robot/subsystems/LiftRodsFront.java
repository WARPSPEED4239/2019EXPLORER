package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftRodsFrontRetract;

public class LiftRodsFront extends Subsystem {

  private DoubleSolenoid mPistons = new DoubleSolenoid(RobotMap.liftRodsFrontSolenoidForward, RobotMap.liftRodsFrontSolenoidReverse);
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftRodsFrontRetract());
  }

  public void retract() {
    mPistons.set(Value.kReverse);
  }

  public void extend() {
    mPistons.set(Value.kForward);
  }
}
