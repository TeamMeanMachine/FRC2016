package org.usfirst.frc.team2471.robot.commands;

import java.nio.channels.SelectableChannel;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class RotateToAngle extends PIDCommand {

	private double angle;
    private double speed;
    private PIDController controller;
    
    public RotateToAngle(double angle, double speed, double tolerance ) {
    	super(Constants.ROTATE_P, Constants.ROTATE_I, Constants.ROTATE_D);
        requires(Robot.drive);
        
    	this.controller = getPIDController();
    	this.speed = speed;
        this.angle = angle;
        controller.setAbsoluteTolerance(tolerance);
        controller.setOutputRange(-1, 1);
        
        controller.setInputRange(-180, 180);
        controller.setContinuous();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setSetpoint(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return controller.onTarget();
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

	@Override
	protected double returnPIDInput() {
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setPower(output, speed);
	}
}
