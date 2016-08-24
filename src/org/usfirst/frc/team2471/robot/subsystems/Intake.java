package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	
	private CANTalon roller;
	private Solenoid actuate;
	private boolean ballIn;
	private DigitalInput intakeBallSensor;

	private boolean suckCanceled;
	
	private Ultrasonic ballUltrasonic;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public Intake(){
		roller = RobotMap.rollerIntake;
		actuate = RobotMap.intakeActuate;
		ballUltrasonic = RobotMap.ballUltrasonic;
		intakeBallSensor = RobotMap.intakeBallSensor;
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
		return !intakeBallSensor.get();
	}
	
	public boolean getBallState() {
		return ballIn;
	}
	
	public void setBallState(boolean state) {
		ballIn = state;
	}
	
	public double getUltrasonic() {
		return ballUltrasonic.getRangeInches();
	}
	
	public void setSuckCanceled(boolean state) {
		suckCanceled = state;
	}
	
	public boolean getSuckCanceled() {
		return suckCanceled;
	}

	public double getBallUltrasonic() {
		return ballUltrasonic.getRangeInches();
	}

}
