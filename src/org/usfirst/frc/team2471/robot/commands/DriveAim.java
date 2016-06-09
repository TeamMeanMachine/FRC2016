package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A different version of manual aim where instead of using the aimdrop we pivot with the
 * drive train. This exists so we can demo the robot's aiming when we aren't able to use the aimdrop
 * in robot demos.
 */
public class DriveAim extends Command {

	public DriveAim() {
		requires(Robot.drive);
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double deadband = 0.1;
		double power = Math.abs(OI.coStick.getRawAxis(4)) > deadband ? OI.coStick.getRawAxis(4) : 0; // Apply deadband
		Robot.drive.setPower(power, 0);
	}

	@Override
	protected boolean isFinished() {
		return OI.coStick.getRawButton(2) || OI.coStick.getRawButton(6);
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
