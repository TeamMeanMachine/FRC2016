package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	private boolean ballInShooter;
	private double endTime;

	public Shoot() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		ballInShooter = true;
		endTime = Timer.getFPGATimestamp() + 5;
		
		if(!Robot.shooter.hasBall()) {
			endTime = 0;
		}
	}

	@Override
	protected void execute() {
		if (ballInShooter && Robot.shooter.hasBall()) {
			ballInShooter = false;
			endTime = Timer.getFPGATimestamp() + 1;
		}
	}

	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() > endTime;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
