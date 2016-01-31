package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Aim extends Command{
	
	public Aim(){
		requires(Robot.aimdrop);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.aimdrop.enable();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.aimdrop.onTarget();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.aimdrop.disable();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.aimdrop.disable();
	}

}
