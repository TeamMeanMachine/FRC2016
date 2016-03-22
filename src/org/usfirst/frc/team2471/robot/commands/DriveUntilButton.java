package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilButton extends Command {
	JoystickButton button;
	
	public DriveUntilButton(JoystickButton button) {
		this.button = button;
	}

	@Override
	protected void initialize() {
//		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		double power = -OI.driverStick.getRawAxis(1);    //Forward & Backwards
		double turn = -OI.driverStick.getRawAxis(4);	 //Left & Right
//		power *= power * power; // Cubic ramp
		
		Robot.drive.setPower(power, turn);
	}

	@Override
	/**
	 * This command is designed to only end when a button is pressed
	 */
	protected boolean isFinished() {
		return button.get();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
