package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	
	public static CANTalon roller;
	public static Solenoid actuate;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public Intake(){
		roller = RobotMap.rollerIntake;
		actuate = RobotMap.intakeActuate;
	}
	
	public void intakeIN(double x){
		roller.set(-x);
		
	}
	
	public void intakeOUT(double x){
		roller.set(-x);
	}
	
	public void intakeSTOP(){
		roller.set(0.0);
	}
	
	public void intakeDown(){
		actuate.set(true);
	}
	
	public void intakeUp(){
		actuate.set(false);
	}
}
