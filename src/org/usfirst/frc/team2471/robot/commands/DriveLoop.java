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
		/*double x = Robot.oi.driverStick.getRawAxis(1);
		double y = Robot.oi.driverStick.getRawAxis(3);
		
		//No cubic functions for now, but possibly later
		x = x * x * x;
		y = y * y * y;
				
		Robot.drive.SetSpeed(x, y);*/
		
		x = SmartDashboard.getNumber("Top");
		y = SmartDashboard.getNumber("Bottom");
		
		Robot.drive.drivetestplz(SmartDashboard.getNumber("Top"), SmartDashboard.getNumber("Bottom"));
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
