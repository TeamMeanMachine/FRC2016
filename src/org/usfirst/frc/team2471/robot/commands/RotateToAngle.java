package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToAngle extends PIDCommand {

	private double angle;
    private double tolerance;
    private boolean relative;
    private PIDController controller;
    
    
    public RotateToAngle(double angle, double speed, double tolerance, boolean relative) {
    	super(Constants.ROTATE_P, Constants.ROTATE_I, Constants.ROTATE_D);
        requires(Robot.drive);
        
    	this.controller = getPIDController();
        this.angle = angle;
        this.tolerance = tolerance;
        this.relative = relative;
        
        controller.setInputRange(-180, 180);
        controller.setContinuous();
    }
    
    public RotateToAngle(double angle, double speed, double tolerance) {
    	this(angle, speed, tolerance, false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(relative) {
        	setSetpoint(RobotMap.gyro.getAngle() + angle);    		
    	}
    	else {
    		setSetpoint(angle); 
    	}
        controller.setAbsoluteTolerance(tolerance);
        
        // Temp
        SmartDashboard.putData("TempPID", controller);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("TestError", controller.getError());
    	return controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.useControllerInput(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
//		Robot.logger.logDebug("Input: " + controller.getError());
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setPower(-output, 0);
	}
}
