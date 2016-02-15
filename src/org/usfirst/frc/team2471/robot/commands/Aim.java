package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim extends Command {
	double error;

	public Aim() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.drive.setAimDrop(true);
		Robot.drive.startAim();
		SmartDashboard.putNumber("Top", .3);
		System.out.println("Got to Aim.initialize()");
	}

	@Override
	protected void execute() {
		// TODO: Error checking for if there is no blobs. Probably should do this by throwing an error in CameraPIDInput and catching if there are no blobs found, so we don't end up shooting a ball at nothing.
		// empty execute command... nice.
	}

	@Override
	protected boolean isFinished() {
		return false;
//		return !Robot.drive.isFinishedAiming();
	}

	@Override
	protected void end() {
		Robot.drive.setAimDrop(false);
		Robot.drive.endAim();

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}

}
