package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainShiftingHighGear;

public class DrivetrainShifting extends Subsystem {

  private DoubleSolenoid drivetrainShiftingSolenoid = new DoubleSolenoid(RobotMap.drivetrainShiftingSolenoidForward, RobotMap.drivetrainShiftingSolenoidReverse);

    public void initDefaultCommand() {
        setDefaultCommand(new DrivetrainShiftingHighGear());
    }
    
    public void drivetrainShiftingHighGear() {
    	drivetrainShiftingSolenoid.set(Value.kForward);
    }
    
    public void drivetrainShiftingLowGear() {
    	drivetrainShiftingSolenoid.set(Value.kReverse);
    }
}