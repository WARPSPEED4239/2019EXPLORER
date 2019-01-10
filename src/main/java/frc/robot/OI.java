package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.LiftWheelsBackDown;
import frc.robot.commands.LiftWheelsFrontDown;

public class OI {
  public XboxController xbox = new XboxController(0);
	public Joystick joystick = new Joystick(1);

	public JoystickButton
		xButtonA,
		xButtonB,
		xButtonX,
		xButtonY;

  public JoystickButton
		jButton1,
		jButton2,
		jbutton7,
		jButton8,
		jButton9,
		jButton10;

	public OI () {
		xButtonA = new JoystickButton(xbox, 1);
		xButtonB = new JoystickButton(xbox, 2);
		xButtonX = new JoystickButton(xbox, 3);
		xButtonY = new JoystickButton(xbox, 4);

		jButton1 = new JoystickButton(joystick, 1);
		jButton2 = new JoystickButton(joystick, 2);
		jbutton7 = new JoystickButton(joystick, 7);
		jButton8 = new JoystickButton(joystick, 8);
		jButton9 = new JoystickButton(joystick, 9);
		jButton10 = new JoystickButton(joystick, 10);

		xButtonA.whileHeld(new LiftWheelsBackDown());
		xButtonB.whileHeld(new LiftWheelsFrontDown());
	}

	public XboxController getController() {
		return xbox;
	}

	public Joystick getJoystick() {
		return joystick;
	}
}
