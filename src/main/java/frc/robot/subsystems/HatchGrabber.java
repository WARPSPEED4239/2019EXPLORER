package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchGrabberRelease;

public class HatchGrabber extends Subsystem {

  private DoubleSolenoid mPiston = new DoubleSolenoid(RobotMap.hatchGrabberSolenoidForward, RobotMap.hatchGrabberSolenoidReverse);
  
  private Compressor compressor = new Compressor(RobotMap.compressor);

  private AnalogInput mPressureSensor = new AnalogInput(RobotMap.pressueSensor);

  private final double DEFAULT_VOLTS = 4.52;
	private final double SLOPE = 250.0;
  private final double Y_INTERCEPT = -25.0;
  
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

  public void turnOffCompressor() {
    compressor.stop();
  }

  public void turnOnCompressor() {
    compressor.start();
  }

  public boolean getCompressorStatus() {
    return compressor.enabled();
  }

  public double getTankPressure() {
		return SLOPE * (mPressureSensor.getVoltage() / DEFAULT_VOLTS) + Y_INTERCEPT; //TODO Tune this equation
  }
  
  public double getPressureSensorVolts() {
    return mPressureSensor.getVoltage();
  }
}
