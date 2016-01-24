package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.subsystems.Catapult;

import edu.wpi.first.wpilibj.command.Command;

public class Launch extends Command {

	public Launch() {
		requires(Robot.launcher);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(Catapult.launching){
			
		}
		
	}

	@Override
	protected boolean isFinished() {
		if(Catapult.launching){
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
