package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpitBall extends Command {

	@Override
	protected void initialize() {
		requires(Robot.intake);		
		if(Robot.intake.getBallState()) {
			Robot.intake.intakeDown();	
			Robot.intake.intakeOut(0.8);
		}
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		return Robot.intake.getBallState();
	}

	@Override
	protected void end() {
		Robot.intake.intakeStop();
		Robot.intake.intakeUp();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
