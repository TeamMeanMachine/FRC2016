package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 * @author FIRST
 */
public class DriveCommand extends Command {
    private double x;
    private double y;
    
    public DriveCommand(double _x, double _y) {
        requires(Robot.drive);
        
        x = _x;
        y = _y;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drive.moveWithController(x, y);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.moveWithController(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
