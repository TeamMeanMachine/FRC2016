package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToAngle extends Command {

	private double targetAngle;
    private double speed, direction;
    
    public RotateToAngle(double angle, double speedZeroToOne ) {
        requires(Robot.drive);
        speed = speedZeroToOne;
        targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double gyroAngle = RobotMap.gyro.getAngle();
		while (gyroAngle > 180.0)
			gyroAngle -= 360.0; 
		while (gyroAngle < -180.0)
			gyroAngle += 360.0; 
		
    	direction = Math.signum(targetAngle - gyroAngle); 
        Robot.drive.setSpeed( direction * -speed, 0 );
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double gyroAngle = (RobotMap.gyro.getAngle() - 360.0) % 360.0;
    	while (gyroAngle > 180.0)
    		gyroAngle -= 360.0; 
    	while (gyroAngle < -180.0)
    		gyroAngle += 360.0; 
    	
    	if (Math.abs(gyroAngle - targetAngle) < 5.0)  //  fudge factor tolerance for heading 
    		return true;
    	
    	if (isTimedOut())
    		return true;

        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.setSpeed( 0, 0 );
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
