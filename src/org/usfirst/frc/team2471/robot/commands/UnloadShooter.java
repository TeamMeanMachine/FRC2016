package org.usfirst.frc.team2471.robot.commands;


import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UnloadShooter extends Command {
	

	@Override
	protected void initialize() {
		requires(Robot.shooter);
		requires(Robot.intake);		
		boolean ballInShooter = Robot.shooter.hasBall();
		boolean ballInIntake = Robot.intake.getBallState();
		if(!ballInShooter && !ballInIntake) {
			return;
		}
		else if(ballInShooter) {
			Robot.shooter.shooterIntakeReverse();
		}		
		Robot.intake.intakeUp();		
		Robot.intake.intakeIn(1);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		return !Robot.shooter.hasBall() && !Robot.intake.getBallState();
	}

	@Override
	protected void end() {
		Robot.shooter.shooterIntakeOff();
		Robot.intake.intakeStop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
