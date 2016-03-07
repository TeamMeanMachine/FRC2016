package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		
		double deadband = 0.20;
		if (turn <= deadband && turn >= -deadband){
			turn = 0;
		}
		if(forward <= deadband && forward >= -deadband){
			forward = 0;
		}
		
		//No cubic functions for now, but possibly later
		//x = x * x * x;
		//y = y * y * y;
		
		boolean useGyro = SmartDashboard.getBoolean("UseGyro", false);
		
		final double FASTRATE = 350;  // TODO: determine how fast is fast
		
		if (useGyro) {
			Robot.drive.turnRateController.enable();
			
			Robot.drive.turnRateController.setSetpoint(turn * FASTRATE);
			
			turn = Robot.drive.getTurnResult();
		}
		else
		{
			Robot.drive.turnRateController.disable();
		}
		
		SmartDashboard.putNumber("DriveDistance", Robot.drive.getEncoderDistance());
		
		//For now we have to make sure not to break it while testing		
		if(!Robot.drive.getAimDropStatus()) {
			// Climb stuff
			double liftPower = OI.coStick.getRawAxis(3);
			if(Math.abs(liftPower) < 0.075) {
				liftPower = 0;
				RobotMap.pto.set(false);
			}
			else {
				RobotMap.pto.set(true);
				turn = 0;
				forward = 0;
			}
		
			Robot.drive.setSpeed(turn, forward - liftPower);  // using the climbing trigger is the same as driving backwards.
		}
		else if(Robot.DEBUGMODE) {
			System.out.println("Robot tried to drive when the aim dropper is down! This should never happen!"); // This should never happen!
		}
		
		// Climb extension stuff
		double extendPower = -OI.coStick.getRawAxis(2);
		if(Math.abs(extendPower) < 0.075) {
			extendPower = 0;
		}
		Robot.drive.setLiftExtension(extendPower);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.setSpeed(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
	
}
