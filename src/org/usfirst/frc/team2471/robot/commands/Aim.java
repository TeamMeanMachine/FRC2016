package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Aim extends Command{
	
	public Aim(){
		requires(Robot.aimdrop);
	}

	@Override
	protected void initialize() {
		Robot.aimdrop.enable();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.aimdrop.onTarget();
	}

	@Override
	protected void end() {
		Robot.aimdrop.disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
