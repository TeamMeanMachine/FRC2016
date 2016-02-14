package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	private static CANTalon left1;
	private static CANTalon left2;
	private static CANTalon right1;
	private static CANTalon right2;
	
	private boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);  // should read from prefs and save to prefs on disabled, find TMMSmartDashboard from 2015 Robot code.
	
	private double requestedPowerLeft, requestedPowerRight;
	
	private PIDController leftController, rightController;
	

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	public Drive(){
		left1 = RobotMap.left1;
		left2 = RobotMap.left2;
		right1 = RobotMap.right1;
		right2 = RobotMap.right2;
		
	
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
	}
	
	public void driveConfig(double x, double y){
//		SmartDashboard.putNumber("Left Encoder: ", RobotMap.leftE.getRate()); TODO: update later
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
		
		bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);

		if (bSpeedControl)
		{
			leftController.enable();
			rightController.enable();
		}
		else {
			leftController.disable();
			rightController.disable();
		}

		if (bSpeedControl)
		{
			
		}
		else
		{
			right1.set( forward + right );
			left1.set( forward - right );
		}
//	}
}
