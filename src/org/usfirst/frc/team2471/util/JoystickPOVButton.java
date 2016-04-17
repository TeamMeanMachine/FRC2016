package org.usfirst.frc.team2471.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickPOVButton extends Button {
	private GenericHID joystick;
	private int povNumber;

	public JoystickPOVButton(GenericHID joystick, int povNumber) {
		this.joystick = joystick;
		this.povNumber = povNumber;
	}
	
	@Override
	public boolean get() {
		return joystick.getPOV() == povNumber;
	}
}
