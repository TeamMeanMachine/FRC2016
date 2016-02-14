package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SuckUpBall extends Command{

	public SuckUpBall()
	{
		requires(Robot.intake);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.intake.intakeDown();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.intake.intakeIN(-1.0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.intake.intakeSTOP();
		Robot.intake.intakeUp();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.intake.intakeSTOP();
		Robot.intake.intakeUp();
	}

}
