package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RotateArmDown extends Command{

	public RotateArmDown() {
		requires(Robot.defenseArm);
	}
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.defenseArm.rotateDown();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.defenseArm.rotateStop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
