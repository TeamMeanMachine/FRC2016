package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Fire extends Command{

	public Fire() {
		requires(Robot.intake);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.intake.intakeOut(1.0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.intake.intakeStop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
