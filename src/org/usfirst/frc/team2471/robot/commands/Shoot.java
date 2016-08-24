package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
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

	@SuppressWarnings("unused")
	@Override
	protected void initialize() {
		ballInShooter = true;
		endTime = Timer.getFPGATimestamp() + 1.75;
		Robot.shooter.shooterIntakeOn();
	}

	@Override
	protected void execute() {
		if(!Constants.DEPEND_ON_SHOOTER_SENSOR) {
			Robot.intake.intakeOut(0.7);
		}
		else if (ballInShooter && Robot.shooter.hasBall()) {
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
		Robot.intake.intakeStop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
