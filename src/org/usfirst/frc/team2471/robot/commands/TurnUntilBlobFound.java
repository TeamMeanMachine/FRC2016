package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnUntilBlobFound extends Command {

    boolean started = false;
    double speed;
    
    public TurnUntilBlobFound(double speedZeroToOne) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
        speed = speedZeroToOne;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!started) {
            started = true;
            Robot.drive.SetSpeed(speed, 0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double blobCount = SmartDashboard.getNumber("BLOB_COUNT");
    	
    	if (blobCount == 1)
    		return true;
    	
    	if (isTimedOut())
    		return true;
    	
    	if (RobotMap.gyro.getAngle() > 45.0) 
    		speed = -speed;
    	else if (RobotMap.gyro.getAngle() < -25)
    		return true;
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        started = false;
        Robot.drive.SetSpeed( 0, 0 );
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
