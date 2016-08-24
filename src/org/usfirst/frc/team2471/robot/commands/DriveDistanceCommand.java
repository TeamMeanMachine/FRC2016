package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 * @author FIRST
 */
public class DriveDistanceCommand extends Command {
    private double distance;
    protected double x;
    private double y;
    private double startDistance;
    
    public DriveDistanceCommand(double _distance, double _x, double _y) {
        requires(Robot.drive);
        
        x = _x;
        y = _y;
        distance = _distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startDistance = Robot.drive.getEncoderDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drive.autoDrive(x, y);
        Robot.logger.logInfo("Difference " + (Robot.drive.getEncoderDistance() - startDistance));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Math.abs(Robot.drive.getEncoderDistance() - startDistance)) > distance || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setPower(0, 0);
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
