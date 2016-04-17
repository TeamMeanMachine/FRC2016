package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class QueueShot extends Command {
	private double startTime;

    public QueueShot() {
    	requires(Robot.intake);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.intakeOut(1.0);
    	Robot.shooter.variableQueue(.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.shooterBallSensor.get() == false || (Timer.getFPGATimestamp() - startTime) >= 1.3 ;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.intake.setBallState(false);
    	Robot.intake.intakeStop();
    	Robot.shooter.shooterIntakeOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
