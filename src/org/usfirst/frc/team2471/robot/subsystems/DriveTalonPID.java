package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTalonPID extends Subsystem {
	
	public static CANTalon left1;
	public static CANTalon left2;
	public static CANTalon right1;
	public static CANTalon right2;
	
	public double prevScale = 1.0;
	
	boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);  // should read from prefs and save to prefs on disabled, find TMMSmartDashboard from 2015 Robot code.
	
	public double requestedPowerLeft, requestedPowerRight;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	public DriveTalonPID(){
		left1 = RobotMap.left1;
		right1 = RobotMap.right1;
		
		//leftController = new PIDController( 0.01, 0.0, 0.03, new WCDLeftPIDSource(), wcdLeftPIDOutput);
		//rightController = new PIDController( 0.01, 0.0, 0.03, new WCDRightPIDSource(), wcdRightPIDOutput);
		
		//leftController = new PIDController(left1.getP(), Ki, Kd, source, output)
		left1.setPID(0.01, 0.0, 0.03);
		right1.setPID(0.01, 0.0, 0.03);
		
		
		
			
		//SmartDashboard.putData("PID Left: ", leftController);
		//SmartDashboard.putData("PID Right: ", rightController);
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
	}
	
	public void driveConfig(double x, double y){
		
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
	}
	
	public void SetLeftPower(double x){
		left1.set(x);
		left2.set(x);
	}
	
	public void SetSpeed(double right, double forward){
		
		bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);

		if (bSpeedControl)
		{
		/*	left1.enable();
			right1.enable();*/
			
			left1.changeControlMode(TalonControlMode.Speed);
			right1.changeControlMode(TalonControlMode.Speed);
		}
		else {
			/*left1.disable();
			right1.disable();*/
			
			left1.changeControlMode(TalonControlMode.PercentVbus);
			right1.changeControlMode(TalonControlMode.PercentVbus);
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
				prevScale = 1.0;
			}
			if (b==0) {
				prevScale = 1.0;
			}
			
			requestedPowerLeft = a;
			requestedPowerRight = b;
			
			left1.setSetpoint(20.0 * a);   // Change for FPS rating
			right1.setSetpoint(20.0 * b);	//Samsies
		}
		else
		{
			SetLeftPower( forward + right );
			SetRightPower( forward - right );
		}
	}
}