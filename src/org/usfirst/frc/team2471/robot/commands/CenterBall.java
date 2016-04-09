package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CenterBall extends Command {
	
	private double ballInTime;
	private boolean ballIn;

    public CenterBall() {
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ballInTime = Timer.getFPGATimestamp();
		ballIn = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.intakeIn(1.0);
    	if(!SmartDashboard.getBoolean("ManualIntake", false)) {
			if(!ballIn && Robot.intake.getIntakeSensor()) {
				ballInTime = Timer.getFPGATimestamp();
				ballIn = true;
			}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((ballIn || SmartDashboard.getBoolean("ManualIntake", false)) && (Timer.getFPGATimestamp() - ballInTime) > 0.3);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intakeStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
