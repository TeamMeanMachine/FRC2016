package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.RobotMap;

/**
 *
 */
public class RotateRelative extends Command {

    private double angleOffset, targetAngle;
    private double speed, direction;
    private double tolerance;

    public RotateRelative(double angle, double speedZeroToOne, double _tolerance) {
        requires(Robot.drive);
        speed = speedZeroToOne;
        angleOffset = angle;
        tolerance = _tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        double gyroAngle = RobotMap.gyro.getAngle();
        while (gyroAngle > 180.0)
            gyroAngle -= 360.0;
        while (gyroAngle < -180.0)
            gyroAngle += 360.0;

        targetAngle = gyroAngle + angleOffset;

        direction = Math.signum(angleOffset);
        if (!isFinished())  // don't turn if we are already there
            Robot.drive.setPower(direction * -speed, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double gyroAngle = (RobotMap.gyro.getAngle() - 360.0) % 360.0;
        while (gyroAngle > 180.0)
            gyroAngle -= 360.0;
        while (gyroAngle < -180.0)
            gyroAngle += 360.0;

        if (Math.abs(gyroAngle - targetAngle) < tolerance)  //  fudge factor tolerance for heading
            return true;

        if (isTimedOut())
            return true;

        return false;
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
}
