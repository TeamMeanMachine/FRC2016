package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	
	private enum BallState {
		ballInShooter,
		shot,
	};
	
	private BallState state;
	private boolean done = false;
	private double shotTime;
	
	public Shoot() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		state = BallState.ballInShooter;
		shotTime = 0.0;
		done = false;
	}

	@Override
	protected void execute() {
		if(Robot.shooter.getShooterState()) {
			switch(state) {
			case ballInShooter:
				Robot.shooter.shootLogic();
				Robot.shooter.shooterIntakeOn();
				if(RobotMap.ballSensorShooter.get() == true) {
					state = BallState.shot;
					shotTime = Timer.getFPGATimestamp();
				}
				break;
			case shot:
				if(Timer.getFPGATimestamp() - shotTime > 1.0) {
					Robot.shooter.stop();
					done = true;
				}
				break;
			}
		}
		else {
			done = true;
		}
			
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	protected void end() {
		new StopShooter().start();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
