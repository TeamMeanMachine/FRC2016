package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLoop extends Command{
	
	public DriveLoop() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double forward = -OI.driverStick.getRawAxis(1);    //Forward & Backwards
		double turn = -OI.driverStick.getRawAxis(4);	 //Left & Right
		Robot.drive.moveWithController(forward, turn);
	
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.setPower(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
	
}
