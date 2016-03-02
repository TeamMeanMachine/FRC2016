package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 * @author FIRST
 */
public class DriveDistanceCommand extends Command {
    private double distance;
    private double x;
    private double y;
    private boolean started = false;
    private double startDistance;
    
    public DriveDistanceCommand(double _distance, double _x, double _y) {
        requires(Robot.drive);
        
        x = _x;
        y = _y;
        distance = _distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!started) {
        	startDistance = Robot.drive.getEncoderDistance();
            started = true;
        }
        Robot.drive.setSpeed(x, y);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.drive.getEncoderDistance() - startDistance) > distance || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setSpeed(0, 0);
        started = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
