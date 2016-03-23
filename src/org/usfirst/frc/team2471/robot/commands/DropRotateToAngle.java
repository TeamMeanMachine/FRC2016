package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class DropRotateToAngle extends PIDCommand {

    public DropRotateToAngle(double setPoint) {
    	super(Constants.AIM_ANGLE_P, Constants.AIM_ANGLE_I, Constants.AIM_ANGLE_D);
    	requires(Robot.drive);
    	setSetpoint(setPoint);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setAimDrop(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(getPIDController().getError()) < 2.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setAimerMotor(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);		
	}
}
