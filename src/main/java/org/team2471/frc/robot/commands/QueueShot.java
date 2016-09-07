package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Constants;
import org.team2471.frc.robot.Robot;

/**
 *
 */
public class QueueShot extends Command {
    private double startTime;

    public QueueShot() {
        requires(Robot.intake);
//    	requires(Robot.shooter);
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
        if (Constants.DEPEND_ON_SHOOTER_SENSOR) {
            return Robot.shooter.hasBall() && (Timer.getFPGATimestamp() - startTime) >= 0.3 || isTimedOut(); // Potentially not working
        } else {
            return Timer.getFPGATimestamp() - startTime >= 0.8 || isTimedOut() || Robot.shooter.hasBall();
        }

//    	return (Timer.getFPGATimestamp() - startTime) > 0.7;
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
