package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterBall extends Command {
	
	private double ballInTime;
	private boolean ballIn;

    public CenterBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ballInTime = 0;
		ballIn = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.intakeIN(1.0);
		if(!ballIn && RobotMap.ballInSensor.get() == false) {
			ballInTime = Timer.getFPGATimestamp();
			ballIn = true;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (ballIn && (Timer.getFPGATimestamp() - ballInTime) > 0.5);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intakeSTOP();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
