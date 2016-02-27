package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualAim extends Command {

	@Override
	protected void initialize() {
		requires(Robot.shooter);
		requires(Robot.drive);
		Robot.shooter.prepAim();
	}

	@Override
	protected void execute() {
		double turnPower = OI.coStick.getRawAxis(2); // Not sure if this is a co pilot thing...
		turnPower *= turnPower * turnPower; // Ramping turn rate with cubic function for enhanced driver control
		
		Robot.drive.setAimerMotor(turnPower); // I guess we should implement PID here...
	}

	@Override
	protected boolean isFinished() {
		return OI.coStick.getRawButton(2);
	}

	@Override
	protected void end() {
		Robot.drive.setAimDrop(false);

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
