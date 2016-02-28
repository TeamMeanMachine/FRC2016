package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	private CANTalon left1;
	private CANTalon right1;
	
	private CANTalon aimer;
	
	private CANTalon liftExtension;
	
	private Solenoid aimDropCylinder;
	
	private boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);  // should read from prefs and save to prefs on disabled, find TMMSmartDashboard from 2015 Robot code.

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	public Drive(){
		left1 = RobotMap.left1;
		right1 = RobotMap.right1;
		aimer = RobotMap.aimer;
		
		liftExtension = RobotMap.liftExtension;
		
		aimDropCylinder = RobotMap.aimDropCylinder;
		
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
	}
	
	public void setAimerMotor(double power) {
		aimer.set(power);
	}
	
	public void setAimDrop(boolean value) {
		aimDropCylinder.set(value);
	}
	public boolean getAimDropStatus() {
		return aimDropCylinder.get();
	}
	
	
	public void driveConfig(double x, double y){
//		SmartDashboard.putNumber("Left Encoder: ", RobotMap.leftE.getRate());
//		SmartDashboard.putNumber("Right Encoder: ", RobotMap.rightE.getRate());
		
		double deadband = 0.05;
		if (x <= deadband && x >= -deadband){
			x = 0;
		}
		if(y <= deadband && y >= -deadband){
			y = 0;
		}
		
		//double speed = Math.abs(RobotMap.leftE.getRate() + RobotMap.rightE.getRate())/2.0;	//Doesn't do anything, will reimplamant when doing testing
	}
	
	public void SetSpeed(double right, double forward){
		
		bSpeedControl = SmartDashboard.getBoolean("Speed Control", false);

		if (bSpeedControl)
		{
			right1.set( forward + right );
			left1.set( forward - right );
		}
		else
		{
			right1.set( forward + right );
			left1.set( forward - right );
		}
	}
	
	public void setLiftExtension(double power) {
//		liftExtension.set(power);
	}
}
