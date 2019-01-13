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

  private DigitalInput liftTopLimitSwitch = new DigitalInput(RobotMap.liftTopLimitSwitch);
  private DigitalInput liftBottomLimitSwitch = new DigitalInput(RobotMap.liftBottomLimitSwitch);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftStop());
  }

  public void liftStop() {
    updateSmartDashboard();
    liftMotor1.set(0.0);
    liftMotor2.set(0.0);
  }

  public void liftUp() {
    updateSmartDashboard();
    liftMotor1.set(1.0);
    liftMotor2.set(1.0);
  }

  public void liftDown() {
    updateSmartDashboard();
    liftMotor1.set(-1.0);
    liftMotor2.set(-1.0);
  }

  public boolean getTopSwitch() {
    return !liftTopLimitSwitch.get();
  }
  
  public boolean getBottomSwitch() {
    return !liftBottomLimitSwitch.get();
  }

  private void updateSmartDashboard() {
    SmartDashboard.putBoolean("Top Limit Switch", getTopSwitch());
    SmartDashboard.putBoolean("Bottom Limit Switch", getBottomSwitch());
  }
}
