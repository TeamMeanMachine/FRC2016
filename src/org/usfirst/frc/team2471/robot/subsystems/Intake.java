package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	
	private CANTalon roller;
	private Solenoid actuate;
	private boolean ballIn;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public Intake(){
		roller = RobotMap.rollerIntake;
		actuate = RobotMap.intakeActuate;
	}
	
	public void intakeIn(double x){
		roller.set(x);
		
	}
	
	public void intakeOut(double x){
		roller.set(-x);
	}
	
	public void intakeStop(){
		roller.set(0.0);
	}
	
	public void intakeDown(){
		actuate.set(true);
	}
	
	public void intakeUp(){
		actuate.set(false);
	}
	
	public boolean getIntakeSensor() {
		return !RobotMap.intakeBallSensor.get();
	}
	
	public boolean getBallState() {
		return ballIn;
	}
	
	public void setBallState(boolean _ballIn) {
		ballIn = _ballIn;
	}
}
