package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.Robot;

/**
 *
 */
public class BallSpit extends Command {
    private double startTime;

    public BallSpit() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.intake.intakeOut(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (isTimedOut()) {
            return true;
        }

        if (SmartDashboard.getBoolean("ManualIntake", false)) {
            return (Timer.getFPGATimestamp() - startTime > 0.2);
        } else {
            return !Robot.intake.getIntakeSensor() && Timer.getFPGATimestamp() - startTime > 0.2;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.intake.intakeStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.intake.setSuckCanceled(false);
        end();
    }
}
