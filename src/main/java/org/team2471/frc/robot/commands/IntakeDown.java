package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.OI;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.commandgroups.PickupBallManual;

/**
 *
 */
public class IntakeDown extends Command {
    int cancelButton;

    public IntakeDown(int cancelButton) {
        requires(Robot.intake);
        this.cancelButton = cancelButton;
    }

    public IntakeDown() {
        this(6);
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.intake.intakeDown();
        Robot.intake.intakeIn(1.0);

        if (Robot.intake.getIntakeSensor()) {
            Robot.intake.setBallState(true);

        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        if (!OI.driverStick.getRawButton(cancelButton)) {
            Robot.intake.setSuckCanceled(true);
            return true;
        }

        return !SmartDashboard.getBoolean("ManualIntake", false) && Robot.intake.getIntakeSensor();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.intake.intakeStop();
        Robot.intake.intakeUp();
        if (SmartDashboard.getBoolean("ManualIntake", true)) {
            new PickupBallManual().start();
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.intake.setSuckCanceled(false);
        end();

    }
}
