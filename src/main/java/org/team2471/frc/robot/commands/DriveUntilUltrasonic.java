package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.RobotMap;

public class DriveUntilUltrasonic extends Command {

    double power;
    double threshold;
    boolean lessThan;


    public DriveUntilUltrasonic(double power, double threshold, boolean lessThan) {
        requires(Robot.drive);
        this.power = power;
        this.threshold = threshold;
        this.lessThan = lessThan;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (!isFinished()) {
            Robot.drive.setPower(0, power);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (lessThan) {
            return RobotMap.backupUltrasonicRight.getRangeInches() <= threshold ||
                    RobotMap.backupUltrasonicLeft.getRangeInches() <= threshold;
        } else {
            return RobotMap.backupUltrasonicRight.getRangeInches() >= threshold &&
                    RobotMap.backupUltrasonicLeft.getRangeInches() >= threshold;
        }
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
