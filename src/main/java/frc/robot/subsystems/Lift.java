package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.LiftStop;

public class Lift extends Subsystem {

  private WPI_TalonSRX mLiftMaster;
  private WPI_TalonSRX mLiftSlave;

  private DigitalInput mLiftLimitSwitch;
  
  public Lift(WPI_TalonSRX liftMaster, WPI_TalonSRX liftSlave, DigitalInput liftLimitSwitch) {
    mLiftMaster = liftMaster;
    mLiftSlave = liftSlave;
    mLiftLimitSwitch = liftLimitSwitch;
    
    mLiftSlave.follow(mLiftMaster);
  }

  public static Lift create() {
    WPI_TalonSRX liftMaster = new WPI_TalonSRX(RobotMap.liftMotor1);
    WPI_TalonSRX liftSlave = new WPI_TalonSRX(RobotMap.liftMotor2);

    DigitalInput liftLimitSwitch = new DigitalInput(RobotMap.liftLimitSwitch);

    return new Lift(liftMaster, liftSlave, liftLimitSwitch);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftStop());
  }

  public void liftStop() {
    updateSmartDashboard();
    mLiftMaster.set(0.0);
  }

  public void liftUp() {
    updateSmartDashboard();
    mLiftMaster.set(1.0);
  }

  public void liftDown() {
    updateSmartDashboard();
    mLiftMaster.set(-1.0);
  }

  public boolean getLiftLimitSwitch() {
    return !mLiftLimitSwitch.get();
  }
  
  private void updateSmartDashboard() {
    SmartDashboard.putBoolean("Lift Limit Switch", getLiftLimitSwitch());
  }
}
