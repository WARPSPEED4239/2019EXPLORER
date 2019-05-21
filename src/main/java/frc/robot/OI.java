package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CargoIntakeHold;
import frc.robot.commands.CargoIntakeIn;
import frc.robot.commands.CargoIntakeOut;
import frc.robot.commands.DrivetrainShiftingHighGear;
import frc.robot.commands.DrivetrainShiftingLowGear;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.commands.HatchGrabberRelease;
import frc.robot.commands.LiftRodBackExtend;
import frc.robot.commands.LiftRodsFrontExtend;
import frc.robot.commands.WristSetPercentOutput;
import frc.robot.commands.automated.DrivetrainVisionAssist;
import frc.robot.commands.automated.GoToPosition;

public class OI {
	public XboxController xbox = new XboxController(0);
	public Joystick joystick = new Joystick(1);
	public Joystick board = new Joystick(2);

	public JoystickButton xButtonA, xButtonB, xButtonX, xButtonY, xButtonLeftBumper, xButtonRightBumper, xButtonLeftStick, xButtonRightStick;

	public JoystickButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9,
			jButton10, jButton11, jButton12;

	public JoystickButton bButton1, bButton2, bButton3, bButton4, bButton5, bButton6, bButton7, bButton8, bButton9,
			bButton10, bButton11;

	public OI() {
		xButtonA = new JoystickButton(xbox, 1);
		xButtonB = new JoystickButton(xbox, 2);
		xButtonX = new JoystickButton(xbox, 3);
		xButtonY = new JoystickButton(xbox, 4);
		xButtonLeftBumper = new JoystickButton(xbox, 5);
		xButtonRightBumper = new JoystickButton(xbox, 6);
		xButtonLeftStick = new JoystickButton(xbox, 9);
		xButtonRightStick = new JoystickButton(xbox, 10);

		jButton1 = new JoystickButton(joystick, 1);
		jButton2 = new JoystickButton(joystick, 2);
		jButton3 = new JoystickButton(joystick, 3);
		jButton4 = new JoystickButton(joystick, 4);
		jButton5 = new JoystickButton(joystick, 5);
		jButton6 = new JoystickButton(joystick, 6);
		jButton7 = new JoystickButton(joystick, 7);
		jButton8 = new JoystickButton(joystick, 8);
		jButton9 = new JoystickButton(joystick, 9);
		jButton10 = new JoystickButton(joystick, 10);
		jButton11 = new JoystickButton(joystick, 11);
		jButton12 = new JoystickButton(joystick, 12);

		bButton1 = new JoystickButton(board, 1);
		bButton2 = new JoystickButton(board, 2);
		bButton3 = new JoystickButton(board, 3);
		bButton4 = new JoystickButton(board, 4);
		bButton5 = new JoystickButton(board, 5);
		bButton6 = new JoystickButton(board, 6);
		bButton7 = new JoystickButton(board, 7);
		bButton8 = new JoystickButton(board, 8);
		bButton9 = new JoystickButton(board, 9);
		bButton10 = new JoystickButton(board, 10);
		bButton11 = new JoystickButton(board, 11);

		xButtonA.whenPressed(new DrivetrainShiftingLowGear());
		xButtonB.whenPressed(new DrivetrainShiftingHighGear());
		xButtonX.whileHeld(new LiftRodBackExtend());
		xButtonY.whileHeld(new LiftRodsFrontExtend());
		xButtonLeftStick.whileHeld(new DrivetrainVisionAssist());
		
		jButton1.whileHeld(new CargoIntakeOut());
		jButton2.whileHeld(new CargoIntakeIn());
		jButton3.whileHeld(new HatchGrabberRelease());
		jButton7.whileHeld(new WristSetPercentOutput(-0.8));
		jButton8.whileHeld(new WristSetPercentOutput(0.8));
		jButton9.whileHeld(new ElevatorSetPercentOutput(-0.6));
		jButton10.whileHeld(new ElevatorSetPercentOutput(0.6));
		jButton11.whenPressed(new CargoIntakeHold());


		bButton1.whenPressed(new GoToPosition(States.Positions.Estop)); //YELLOW

		bButton2.whenPressed(new GoToPosition(States.Positions.Floor)); //BLUE 1
		bButton3.whenPressed(new GoToPosition(States.Positions.Stored)); //BLUE 2
		
		bButton4.whenPressed(new GoToPosition(States.Positions.HatchLevelOne)); //WHITE 1
		bButton5.whenPressed(new GoToPosition(States.Positions.HatchLevelTwo)); //WHITE 2
		bButton6.whenPressed(new GoToPosition(States.Positions.HatchLevelThree)); //WHITE 3

		bButton7.whenPressed(new GoToPosition(States.Positions.CargoRocketOne)); //RED 1
		bButton8.whenPressed(new GoToPosition(States.Positions.CargoRocketTwo)); //RED 2
		bButton9.whenPressed(new GoToPosition(States.Positions.CargoRocketThree)); //RED 3
		bButton10.whenPressed(new GoToPosition(States.Positions.CargoIntakeElevated)); //BLUE 3
		bButton11.whenPressed(new GoToPosition(States.Positions.CargoPlayerStationAndCargoShip)); //BLUE 4
		}

	public XboxController getController() {
		return xbox;
	}

	public Joystick getJoystick() {
		return joystick;
	}

	public Joystick getBoard() {
		return board;
	}
}
