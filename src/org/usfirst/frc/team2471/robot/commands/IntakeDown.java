package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commandgroups.IntakeAuto;
import org.usfirst.frc.team2471.robot.commandgroups.PickupBallManual;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeDown extends Command {

    public IntakeDown() {
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (RobotMap.ballSensorShooter.get()==true) {
	    	Robot.intake.intakeDown();
	    	Robot.intake.intakeIn(1.0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.intake.getIntakeSensor()) {
    		Robot.intake.setBallState(true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(SmartDashboard.getBoolean("ManualIntake", false)) {
    		return false;
    	}
    	else {
    		return Robot.intake.getIntakeSensor();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intakeStop();
    	Robot.intake.intakeUp();
    	if (SmartDashboard.getBoolean("ManualIntake", true)){
    		new PickupBallManual().start();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
