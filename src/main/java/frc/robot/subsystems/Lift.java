package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.LiftStop;

public class Lift extends Subsystem {

  private WPI_TalonSRX liftMotor1 = new WPI_TalonSRX(RobotMap.liftMotor1);
  private WPI_TalonSRX liftMotor2 = new WPI_TalonSRX(RobotMap.liftMotor2);

  private DigitalInput liftLimitSwitch = new DigitalInput(RobotMap.liftLimitSwitch);
 
  public Lift() {
    liftMotor2.follow(liftMotor1);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftStop());
  }

  public void liftStop() {
    updateSmartDashboard();
    liftMotor1.set(0.0);
  }

  public void liftUp() {
    updateSmartDashboard();
    liftMotor1.set(1.0);
  }

  public void liftDown() {
    updateSmartDashboard();
    liftMotor1.set(-1.0);
  }

  public boolean getLiftLimitSwitch() {
    return !liftLimitSwitch.get();
  }
  
  private void updateSmartDashboard() {
    SmartDashboard.putBoolean("Lift Limit Switch", getLiftLimitSwitch());
  }
}
