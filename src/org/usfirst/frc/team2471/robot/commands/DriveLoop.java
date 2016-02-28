package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
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
		double x = -OI.driverStick.getRawAxis(1);
		double y = -OI.driverStick.getRawAxis(2);
		
		//No cubic functions for now, but possibly later
		//x = x * x * x;
		//y = y * y * y;
		
		//For now we have to make sure not to break it while testing		
		if(!Robot.drive.getAimDropStatus()) {
			Robot.drive.SetSpeed(x, y);
		}
		else if(Robot.DEBUGMODE) {
			System.out.println("Robot tried to drive when the aim dropper is down! This should never happen!"); // This should never happen!
		}
		
		// Climb extension stuff
		double liftPower = OI.coStick.getRawAxis(2);
		if(Math.abs(liftPower) < 0.075) {
			liftPower = 0;
		}
		Robot.drive.setLiftExtension(liftPower);
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
