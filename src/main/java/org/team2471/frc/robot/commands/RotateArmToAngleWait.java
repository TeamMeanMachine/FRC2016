package org.team2471.frc.robot.commands;

import org.team2471.frc.robot.Robot;

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
