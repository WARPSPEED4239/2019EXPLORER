package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainShiftingHighGear;

public class DrivetrainShifting extends Subsystem {

  private DoubleSolenoid mPiston = new DoubleSolenoid(RobotMap.drivetrainShiftingSolenoidForward, RobotMap.drivetrainShiftingSolenoidReverse);

    public void initDefaultCommand() {
        setDefaultCommand(new DrivetrainShiftingHighGear());
    }
    
    public void highGear() {
    	mPiston.set(Value.kForward);
    }
    
    public void lowGear() {
    	mPiston.set(Value.kReverse);
    }
}