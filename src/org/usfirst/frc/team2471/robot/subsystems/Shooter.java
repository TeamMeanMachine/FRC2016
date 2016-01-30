package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.Shoot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	
	public CANTalon motor1;
	public CANTalon motor2;

	protected void initDefaultCommand() {
	//	setDefaultCommand(new Shoot());
	}

	public Shooter(){
		motor1 = RobotMap.shootMotor1;
		motor2 = RobotMap.shootMotor2;
	}
	

	public void shoot(double x, double y) {
		// TODO Auto-generated method stub
		motor1.set(x);
		motor2.set(y);
	}

	public void stop() {
		// TODO Auto-generated method stub
		motor1.set(0.0);
		motor2.set(0.0);
	}
}
