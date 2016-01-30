package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLoop extends Command{
	
	public DriveLoop(){
		requires(Robot.drive);
	}
	@Override
	protected void initialize() {
		
		
	}

	@Override
	protected void execute() {
		double x = Robot.oi.driveStick.getRawAxis(1);
		double y = Robot.oi.driveStick.getRawAxis(3);
		
		//No cubic functions for now, but possibly later
		x = x * x * x;
		y = y * y * y;
				
		Robot.drive.SetSpeed(x, y);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
		
	}
	
}
