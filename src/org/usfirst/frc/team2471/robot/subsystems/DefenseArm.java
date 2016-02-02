package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DefenseArm extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void rotateUp(){
		RobotMap.rotateArm.set(1.0);
	}
	
	public void rotateDown(){
		RobotMap.rotateArm.set(-1.0);
	}
	
	public void rotateStop(){
		RobotMap.rotateArm.set(0.0);
	}

}
