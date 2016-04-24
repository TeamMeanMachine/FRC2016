package org.usfirst.frc.team2471.robot.commands;
import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilUltrasonic extends Command {

	double power;
	double threshold;
	boolean lessThan;
	
	
    public DriveUntilUltrasonic(double power, double threshold, boolean lessThan) {
        requires(Robot.drive);
        this.power = power;
        this.threshold = threshold;
        this.lessThan = lessThan;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!isFinished())
    		Robot.drive.setSpeed(0, power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(lessThan) {
    		return RobotMap.backupUltrasonicRight.getRangeInches() <= threshold || RobotMap.backupUltrasonicLeft.getRangeInches() <= threshold || isTimedOut();
//    		return RobotMap.backupUltrasonicRight.getRangeInches() <= threshold || isTimedOut();
    	}
    	else {
    		return (RobotMap.backupUltrasonicRight.getRangeInches() >= threshold && RobotMap.backupUltrasonicLeft.getRangeInches() >= threshold) || isTimedOut();
//    		return (RobotMap.backupUltrasonicRight.getRangeInches() >= threshold ) || isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
