package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CargoIntakeIn;
import frc.robot.commands.CargoIntakeOut;
import frc.robot.commands.DrivetrainShiftingHighGear;
import frc.robot.commands.DrivetrainShiftingLowGear;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.commands.HatchGrabberExtend;
import frc.robot.commands.HatchGrabberRetract;
import frc.robot.commands.LiftWheelDown;
import frc.robot.commands.WristSetPercentOutput;
import frc.robot.commands.automated.GoToPosition;

public class OI {
	public XboxController xbox = new XboxController(0);
	public Joystick joystick = new Joystick(1);
	public Joystick board = new Joystick(2);

	public JoystickButton xButtonA, xButtonB, xButtonX, xButtonY, xButtonLeftStick, xButtonRightStick;

	public JoystickButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9,
			jButton10, jButton11, jButton12;

	public JoystickButton bButton1, bButton2, bButton3, bButton4, bButton5, bButton6, bButton7, bButton8, bButton9,
			bButton10, bButton11;

	public OI() {
		xButtonA = new JoystickButton(xbox, 1);
		xButtonB = new JoystickButton(xbox, 2);
		xButtonX = new JoystickButton(xbox, 3);
		xButtonY = new JoystickButton(xbox, 4);
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

		jButton1.whileHeld(new CargoIntakeOut());
		jButton2.whileHeld(new CargoIntakeIn());
		jButton3.whenPressed(new HatchGrabberRetract());
		jButton4.whenPressed(new HatchGrabberExtend());

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		bButton1.whenPressed(new GoToPosition(States.Positions.Estop));
=======
=======
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.
=======
		xButtonY.whenPressed(new DrivetrainShiftingHighGear());
		xButtonB.whenPressed(new DrivetrainShiftingLowGear());
		xButtonX.whileHeld(new LiftWheelDown());

>>>>>>> parent of 7240df4... Added new end game code
		/*jButton5.whenPressed(new WristSetPosition(0.0));
		jButton6.whenPressed(new WristManualControl());
		bButton1.whileHeld(new ElevatorSetPostitionWithJoystick());
		bButton2.whileHeld(new ElevatorSetPosition(30.0));*/
<<<<<<< HEAD
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.
=======
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.

		bButton1.whenPressed(new ElevatorSetPercentOutput(0.0));
		bButton1.whenPressed(new WristSetPercentOutput(0.0));
		
<<<<<<< HEAD
<<<<<<< HEAD
		bButton4.whenPressed(new GoToPosition(States.Positions.HatchLevelOne));
		bButton5.whenPressed(new GoToPosition(States.Positions.HatchLevelTwo));
		bButton6.whenPressed(new GoToPosition(States.Positions.HatchLevelThree));

		bButton7.whenPressed(new GoToPosition(States.Positions.CargoRocketOne));
		bButton8.whenPressed(new GoToPosition(States.Positions.CargoRocketTwo));
		bButton9.whenPressed(new GoToPosition(States.Positions.CargoRocketThree));
		bButton10.whenPressed(new GoToPosition(States.Positions.CargoIntakeElevated));
		bButton11.whenPressed(new GoToPosition(States.Positions.CargoPlayerStationAndCargoShip));
=======
=======
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
		//xButtonX.whileHeld(new RampsUp());
		//xButtonA.whileHeld(new RampsDown());
		xButtonY.whenPressed(new DrivetrainShiftingHighGear());
		xButtonB.whenPressed(new DrivetrainShiftingLowGear());
		xButtonRightStick.whileHeld(new LiftWheelDown());

		/*jButton5.whenPressed(new WristSetPosition(0.0));
		jButton6.whenPressed(new WristManualControl());
		bButton1.whileHeld(new ElevatorSetPostitionWithJoystick());
		bButton2.whileHeld(new ElevatorSetPosition(30.0));*/

		bButton1.whenPressed(new ElevatorSetPercentOutput(0.0));
		bButton1.whenPressed(new WristSetPercentOutput(0.0));
=======
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.
=======
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.
		bButton6.whenPressed(new GoToPosition(States.Positions.HatchFloor));
		bButton7.whenPressed(new GoToPosition(States.Positions.HatchLevelOne));
		bButton8.whenPressed(new GoToPosition(States.Positions.HatchLevelTwo));
		bButton9.whenPressed(new GoToPosition(States.Positions.HatchLevelThree));

		bButton2.whenPressed(new GoToPosition(States.Positions.CargoFloor));
		bButton4.whenPressed(new GoToPosition(States.Positions.CargoRocketOne));
		bButton3.whenPressed(new GoToPosition(States.Positions.CargoRocketTwo));
		bButton5.whenPressed(new GoToPosition(States.Positions.CargoRocketThree));
<<<<<<< HEAD
<<<<<<< HEAD
		bButton11.whenPressed(new GoToPosition(States.Positions.CargoPlayerStation));
		bButton10.whenPressed(new GoToPosition(States.Positions.CargoCargoShip));
<<<<<<< HEAD
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
=======
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
=======
		bButton10.whenPressed(new GoToPosition(States.Positions.CargoPlayerStationAndCargoShip));
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.
=======
		bButton10.whenPressed(new GoToPosition(States.Positions.CargoPlayerStationAndCargoShip));
>>>>>>> parent of 7e43e75... Added code state boolean, cleaned up OI, changed resets for sensors, reworked States, added many TODO's, fixed AutonomusCommand.
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
