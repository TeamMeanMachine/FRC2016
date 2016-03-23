package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnUntilBlobFound extends Command {

    private double speed;
    
    public TurnUntilBlobFound(double speedZeroToOne) {
        requires(Robot.drive);
        speed = speedZeroToOne;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.ringLight.set(true);
    	if(!isFinished()) {
            Robot.drive.setSpeed(-speed, 0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double blobCount = SmartDashboard.getNumber("BLOB_COUNT",0);
    	
    	if (blobCount == 1)
    		return true;
    	
    	if (isTimedOut())
    		return true;
    	
//    	if (RobotMap.gyro.getAngle() > 45.0) 
//    		speed = -speed;
//    	else if (RobotMap.gyro.getAngle() < -25)
//    		return true;
    	if (RobotMap.navx.getAngle() > 45.0) 
    		speed = -speed;
    	else if (RobotMap.navx.getAngle() < -25)
    		return true;
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.setSpeed( 0, 0 );
    	RobotMap.ringLight.set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
