package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	private CANTalon leftDrive;
	private CANTalon rightDrive;
	
	private CANTalon aimer;
	
	private CANTalon liftExtension;
	
	private Solenoid aimDropCylinder;
	
	private boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);  // should read from prefs and save to prefs on disabled
	
	public PIDController turnRateController;
	
	private double turnResult = 0.0;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	public Drive(){
		leftDrive = RobotMap.leftDrive;
		rightDrive = RobotMap.rightDrive;
		aimer = RobotMap.aimer;
		
		liftExtension = RobotMap.liftExtension;
		
		aimDropCylinder = RobotMap.aimDropCylinder;
		
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
		
		turnRateController = new PIDController( Constants.TURN_P, Constants.TURN_I, Constants.TURN_D, new turnRatePIDSource(), new turnRatePIDOutput());
		turnRateController.setOutputRange(-1.0, 1.0);
		
		SmartDashboard.putData("TurnPID", turnRateController);
	}
	
	class turnRatePIDSource implements PIDSource {
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return RobotMap.gyro.getPIDSourceType();
		}

		@Override
		public double pidGet() {
			return -RobotMap.gyro.getRate();
		}
	}
	
	class turnRatePIDOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			turnResult += output;
			if(turnResult > 1.0) {
				turnResult = 1.0;
			}
			else if(turnResult < -1.0) {
				turnResult = -1.0;
			}
		}
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
	
	public void setSpeed(double right, double forward){
		
		bSpeedControl = SmartDashboard.getBoolean("Speed Control", false);

		if (bSpeedControl)
		{
			rightDrive.set( forward + right );
			leftDrive.set( -(forward - right) );
		}
		else
		{
			rightDrive.set( forward + right );
			leftDrive.set( -(forward - right) );
		}
		
//		SmartDashboard.putNumber("Accel X", RobotMap.accelerometer.getX());
//		SmartDashboard.putNumber("Accel Y", RobotMap.accelerometer.getY());
//		SmartDashboard.putNumber("Accel Z", RobotMap.accelerometer.getZ());z
	}
	
	public void setLiftExtension(double power) {
		liftExtension.set(power);
	}

	public double getTurnResult() {
		return turnResult;
	}

	public void setTurnResult(double turnResult) {
		this.turnResult = turnResult;
	}
	
	public double getEncoderDistance() {
		return ( Math.abs( leftDrive.getEncPosition() ) + Math.abs( rightDrive.getEncPosition() ) ) / 2.0;
	}
	
	public void resetEncoders() {
		leftDrive.setEncPosition(0);
		rightDrive.setEncPosition(0);
	}
}
