package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	
	public Shoot() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.shooter.shootLogic();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
