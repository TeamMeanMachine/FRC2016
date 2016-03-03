package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	
	private enum BallState {
		ballInShooter,
		shot,
	};
	
	private BallState state;
	private boolean done = false;
	
	public Shoot() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		state = BallState.ballInShooter;
	}

	@Override
	protected void execute() {
		switch(state) {
		case ballInShooter:
			Robot.shooter.shootLogic();
			if(RobotMap.ballSensorShooter.get() == true) {
				state = BallState.shot;
			}
			break;
		case shot:
			Robot.shooter.stop();
			done = true;
			break;
		}
			
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
