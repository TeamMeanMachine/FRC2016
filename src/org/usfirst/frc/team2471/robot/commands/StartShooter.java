package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StartShooter extends Command {

    public StartShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
		SmartDashboard.putBoolean( "Shoot", true );
		System.out.println("StartShooter");
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
    }
}
