package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RotateArmUp extends Command{

	public RotateArmUp() {
		// TODO Auto-generated constructor stub
		requires(Robot.defenseArm);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		while(RobotMap.upperArmLimit.get() == false){
			Robot.defenseArm.rotateUp();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Robot.defenseArm.rotateStop();
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.defenseArm.rotateStop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.defenseArm.rotateStop();
	}

}
