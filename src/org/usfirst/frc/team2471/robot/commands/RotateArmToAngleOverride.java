package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateArmToAngleOverride extends Command {
	
	private double targetAngle;

    public RotateArmToAngleOverride( double angle ) {
		requires(Robot.defenseArm);
		targetAngle = angle;
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
		Robot.defenseArm.overrideSetTargetAngle(targetAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	end();
    }
}
