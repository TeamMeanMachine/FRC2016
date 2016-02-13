package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DefenseArm extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void rotateUp(){
		RobotMap.portcullisArmLeft.set(1.0);
	}
	
	public void rotateDown(){
		RobotMap.portcullisArmLeft.set(-1.0);
	}
	
	public void rotateStop(){
		RobotMap.portcullisArmLeft.set(0.0);
	}

}
