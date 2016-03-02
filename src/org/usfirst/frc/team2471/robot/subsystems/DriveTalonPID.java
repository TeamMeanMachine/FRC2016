package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTalonPID extends Subsystem {
	
	private CANTalon leftDrive;
	private CANTalon rightDrive;
	
	public double prevScale = 1.0;
	
	boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);
	
	public double requestedPowerLeft, requestedPowerRight;
	
	public DriveTalonPID() {
		leftDrive = RobotMap.leftDrive;
		rightDrive = RobotMap.rightDrive;
		
		leftDrive.setPID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D);
		rightDrive.setPID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D);
	
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}

	public void setRightPower(double x) {
		rightDrive.set(x);
	}
	
	public void setLeftPower(double x){
		leftDrive.set(x);
	}
	
	public void setSpeed(double right, double forward){
		
		bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);

		if (bSpeedControl)
		{
		/*	left1.enable();
			right1.enable();*/
			
			leftDrive.changeControlMode(TalonControlMode.Speed);
			rightDrive.changeControlMode(TalonControlMode.Speed);
		}
		else {
			/*left1.disable();
			right1.disable();*/
			
			leftDrive.changeControlMode(TalonControlMode.PercentVbus);
			rightDrive.changeControlMode(TalonControlMode.PercentVbus);
		}

		if (bSpeedControl) {
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
			
			leftDrive.setSetpoint(20.0 * a);   // Change for FPS rating
			rightDrive.setSetpoint(20.0 * b);	//Samsies
		}
		else {
			setLeftPower( forward + right );
			setRightPower( forward - right );
		}
	}
}
