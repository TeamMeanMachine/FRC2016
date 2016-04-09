package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SpitBall extends Command {
	double startTime;
	
	public SpitBall() {
		requires(Robot.intake);		
	}

	@Override
	protected void initialize() {
		Robot.intake.intakeStop();
		Robot.intake.intakeDown();	
	}

	@Override
	protected void execute() {
		Robot.intake.intakeOut(.7);
	}

	@Override
	protected boolean isFinished() {
		return (Timer.getFPGATimestamp() - startTime) > .7;
	}

	@Override
	protected void end() {
		Robot.intake.intakeStop();
		Robot.intake.intakeUp();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
