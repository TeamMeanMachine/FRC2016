package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpitOutBall extends Command{

	public SpitOutBall() {
		// TODO Auto-generated constructor stub
		requires(Robot.intake);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.intake.intakeOUT(1.0);
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
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.intake.intakeSTOP();
	}

}
