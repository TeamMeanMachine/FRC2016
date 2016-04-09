package org.usfirst.frc.team2471.robot.commands;


import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class UnloadShooter extends Command {
	
	private double startTime;
	public UnloadShooter() {
		requires(Robot.shooter);
		requires(Robot.intake);		
	}
	@Override
	protected void initialize() {
		double startTime = Timer.getFPGATimestamp();
		Robot.intake.intakeUp();	
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.shooterIntakeReverse();
		Robot.intake.intakeIn(1);
	}

	@Override
	protected boolean isFinished() {
		return (Timer.getFPGATimestamp() - startTime) > 0.7;
	}

	@Override
	protected void end() {
		Robot.shooter.shooterIntakeOff();
		Robot.intake.intakeStop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
