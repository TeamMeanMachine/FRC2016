package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.CameraPIDInput;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	private CANTalon left1;
	private CANTalon right1;
	
	public CANTalon aimer; // TODO: temp public
	
	private Solenoid aimDropCylinder;
	
	private boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);  // should read from prefs and save to prefs on disabled, find TMMSmartDashboard from 2015 Robot code.
	
	private PIDController aimController;
	
	private CameraPIDInput shotInTheDark;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	class newPIDOutput implements PIDOutput {
		CANTalon motor;
		public newPIDOutput(CANTalon motor) {
			this.motor = motor;
		}
		@Override
		public void pidWrite(double output) {
			SmartDashboard.putNumber("PID_2", output);
			motor.set(output);
		}
		
	}
	
	public Drive(){
		left1 = RobotMap.left1;
		right1 = RobotMap.right1;
		aimer = RobotMap.aimer;
		
		aimDropCylinder = RobotMap.aimDropCylinder;
		
		shotInTheDark = new CameraPIDInput();
		aimController = new PIDController(0.045, 0, 0, shotInTheDark, new newPIDOutput(aimer)); // TODO: Magic numbers
//		aimController.setAbsoluteTolerance(3); // TODO: Magic number
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
	}
	
	public boolean isFinishedAiming() {
		return aimController.onTarget();
	}
	
	public void startAim() {
		aimController.enable();
		aimController.setSetpoint(0);
	}
	
	public void endAim() {
		aimController.disable();
	}
	
	public void setAimDrop(boolean value) {
		aimDropCylinder.set(value);
	}
	
	
	public void driveConfig(double x, double y){ // TODO: Figure out what this does
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

//		if (bSpeedControl)
//		{
//			leftController.enable();
//			rightController.enable();
//		}
//		else {
//			leftController.disable();
//			rightController.disable();
//		}

		if (bSpeedControl)
		{
			
		}
		else
		{
			right1.set( forward + right );
			left1.set( forward - right );
		}
	}
}
