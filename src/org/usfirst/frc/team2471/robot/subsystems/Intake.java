package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	
	public static CANTalon roller;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public Intake(){
		roller = RobotMap.rollerIntake;
	}
	
	public void intakeIN(double x){
		roller.set(x);
	}
	
	public void intakeOUT(double x){
		roller.set(x);
	}
	
	public void intakeSTOP(){
		
	}
}
