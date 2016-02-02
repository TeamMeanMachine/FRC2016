package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	public static CANTalon left1;
	public static CANTalon left2;
	public static CANTalon left3;
	public static CANTalon right1;
	public static CANTalon right2;
	public static CANTalon right3;
	
	boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);  // should read from prefs and save to prefs on disabled, find TMMSmartDashboard from 2015 Robot code.
	
	WCDLeftPIDOutput wcdLeftPIDOutput;
	WCDRightPIDOutput wcdRightPIDOutput;
	public double requestedPowerLeft, requestedPowerRight;
	
	public PIDController leftController, rightController;
	
	class WCDLeftPIDSource implements PIDSource {
		public double pidGet(){
			return RobotMap.leftE.getRate();
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	class WCDRightPIDSource implements PIDSource {
		public double pidGet(){
			return RobotMap.rightE.getRate();
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	class WCDLeftPIDOutput implements PIDOutput {
		public double prevScale = 1.0;
		public void pidWrite(double output){
			output *= Math.signum( requestedPowerLeft );
			prevScale += output;
			if(prevScale >=10.0 ){
				prevScale = 10.0;
			}
			else if(prevScale <= 0.01){
				prevScale = 0.01;
			}
			SetLeftPower( prevScale * requestedPowerLeft );
		}
	}
	
	class WCDRightPIDOutput implements PIDOutput {
		public double prevScale = 1.0;
		public void pidWrite(double output){
			output *= Math.signum( requestedPowerRight );
			prevScale += output;
			if(prevScale >=10.0 ){
				prevScale = 10.0;
			}
			else if(prevScale <= 0.01){
				prevScale = 0.01;
			}
			SetRightPower( prevScale * requestedPowerRight );
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	public Drive(){
		left1 = RobotMap.left1;
		left2 = RobotMap.left2;
		left3 = RobotMap.left3;
		right1 = RobotMap.right1;
		right2 = RobotMap.right2;
		right3 = RobotMap.right3;
		
		wcdLeftPIDOutput = new WCDLeftPIDOutput();
		wcdRightPIDOutput = new WCDRightPIDOutput();
		
		leftController = new PIDController( 0.01, 0.0, 0.03, new WCDLeftPIDSource(), wcdLeftPIDOutput);
		rightController = new PIDController( 0.01, 0.0, 0.03, new WCDRightPIDSource(), wcdRightPIDOutput);
			
		SmartDashboard.putData("PID Left: ", leftController);
		SmartDashboard.putData("PID Right: ", rightController);
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
	}
	
	public void driveConfig(double x, double y){
		SmartDashboard.putNumber("Left Encoder: ", RobotMap.leftE.getRate());
		SmartDashboard.putNumber("Right Encoder: ", RobotMap.rightE.getRate());
		
		double deadband = 0.05;
		if (x <= deadband && x >= -deadband){
			x = 0;
		}
		if(y <= deadband && y >= -deadband){
			y = 0;
		}
		
		//double speed = Math.abs(RobotMap.leftE.getRate() + RobotMap.rightE.getRate())/2.0;	//Doesn't do anything, will reimplamant when doing testing
	}

	public void SetRightPower(double x) {
		right1.set(x);
		right2.set(x);
		right3.set(x);
	}
	
	public void SetLeftPower(double x){
		left1.set(x);
		left2.set(x);
		left3.set(x);
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
			double a = forward + right;
			double b = forward - right;
			double maxPower = Math.max( Math.abs(a) , Math.abs(b) );
			if (maxPower > 1.0){
				a /= maxPower;
				b /= maxPower;
			}
			
			if (a==0) {
				wcdLeftPIDOutput.prevScale = 1.0;
			}
			if (b==0) {
				wcdRightPIDOutput.prevScale = 1.0;
			}
			
			requestedPowerLeft = a;
			requestedPowerRight = b;
			leftController.setSetpoint( 20.0 * a );
			rightController.setSetpoint( 20.0 * b );
		}
		else
		{
			SetLeftPower( forward + right );
			SetRightPower( forward - right );
		}
	}
}
