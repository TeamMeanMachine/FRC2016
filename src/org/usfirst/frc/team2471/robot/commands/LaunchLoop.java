package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.Catapult;

import edu.wpi.first.wpilibj.command.Command;

public class LaunchLoop extends Command {

	public LaunchLoop() {
		requires(Robot.launcher);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(!RobotMap.launcherIsDown.get() && Catapult.launching == false){
			Robot.launcher.goDown();
		}
		
	}

	@Override
	protected boolean isFinished() {
		if(RobotMap.launcherIsDown.get()){
			return true;
		}
		else{
			return false;
		}
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
