package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SuckUpBall extends Command {
	
	private double ballInTime;
	private boolean ballIn;

	public SuckUpBall()
	{
		requires(Robot.intake);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.intake.intakeDown();
		ballInTime = 0;
		ballIn = false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.intake.intakeIN(1.0);
		if(!ballIn && RobotMap.ballInSensor.get() == false) {
			ballInTime = Timer.getFPGATimestamp();
			ballIn = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return (ballIn && (Timer.getFPGATimestamp() - ballInTime) > 0.25);
	}

	@Override
	protected void end() {
		Robot.intake.intakeSTOP();
		Robot.intake.intakeUp();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
