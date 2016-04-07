package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StopShooter extends Command {

    public StopShooter() {
		requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	System.out.println("StopShooter Started");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
		Robot.shooter.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	System.out.println("StopShooter Finished");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    }
}
