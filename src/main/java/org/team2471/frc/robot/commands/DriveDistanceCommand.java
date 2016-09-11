package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Robot;

public class DriveDistanceCommand extends Command {
    private double targetDistance;
    double x;
    private double y;
    private double startDistance;

    public DriveDistanceCommand(double _distance, double _x, double _y) {
        requires(Robot.drive);

        x = _x;
        y = _y;
        targetDistance = _distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startDistance = Robot.drive.getEncoderDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drive.autoDrive(x, y);
        Robot.logger.logInfo("Difference " + (Robot.drive.getEncoderDistance() - startDistance));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.drive.getEncoderDistance() - startDistance)) > targetDistance || isTimedOut();
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
