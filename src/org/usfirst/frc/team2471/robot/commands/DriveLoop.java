package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveLoop extends Command{
	
	double x, y;
	
	public DriveLoop(){
		requires(Robot.drive);
		

		SmartDashboard.putNumber("Top", x);
		SmartDashboard.putNumber("Bottom", y);
	}
	@Override
	protected void initialize() {
		
		
	}

	@Override
	protected void execute() {
		double x = -Robot.oi.driverStick.getRawAxis(1);
		double y = -Robot.oi.driverStick.getRawAxis(2);
		
		//No cubic functions for now, but possibly later
		//x = x * x * x;
		//y = y * y * y;
		
		
		//For now we have to make sure not to break it while testing		
		if(!Robot.drive.getAimDropStatus()) {
			Robot.drive.SetSpeed(x, y);
		}
		else if(Robot.DEBUGMODE) {
			System.out.println("Robot tried to drive when the aim dropper is down! This should never happen!!!"); // This should never happen!
		}
		/*Robot.drivePreTalon.SetRightPower(x);
		Robot.drivePreTalon.SetLeftPower(y);*/
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
