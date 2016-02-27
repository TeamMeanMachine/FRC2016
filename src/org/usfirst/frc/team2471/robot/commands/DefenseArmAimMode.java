package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DefenseArmAimMode extends Command {
	public static final double AIM_ANGLE = 40; // TODO: Get an angle, I don't know if this will work
	
	public DefenseArmAimMode() {
		requires(Robot.defenseArm);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		Robot.defenseArm.setTargetAngle(AIM_ANGLE);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.drive.getAimDropStatus();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
