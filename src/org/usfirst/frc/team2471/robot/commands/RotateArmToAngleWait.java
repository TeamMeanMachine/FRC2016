package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

public class RotateArmToAngleWait extends RotateArmToAngle {
	double error;
	public RotateArmToAngleWait(double angle, double error) {
		super(angle);
		this.error = error;
	}
	
	// Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return (Math.abs(Robot.defenseArm.getTargetError()) <= this.error) || isTimedOut();
    }

}
