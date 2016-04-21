package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * For collecting data on the latency between the RoboRIO and the Intel stick.
 */
public class PingSmartDashboard extends Command {
	private double startTime;
	
	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
		SmartDashboard.putBoolean("PING", true);

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		return !SmartDashboard.getBoolean("PING");
	}

	@Override
	protected void end() {
		double timeDiff = (Timer.getFPGATimestamp() - startTime) / 1000;
		Robot.logger.logInfo("Ping result: " + timeDiff + "ms");
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
