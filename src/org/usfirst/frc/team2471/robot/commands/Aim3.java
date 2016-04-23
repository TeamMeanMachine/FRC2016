package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */	
public class Aim3 extends PIDCommand { // This is all broken dont use
	private double offsetAngle;
	private boolean finished = false; 
	private int samples = 0;
	
	private double sampleTime;
	private boolean findSample = false;
	

	public Aim3(boolean finishOnTarget) { // TODO: Use boolean
		super(Constants.AIM_2_P, Constants.AIM_2_I, Constants.AIM_2_D);
		requires(Robot.drive);
		requires(Robot.shooter);
		
		getPIDController().setAbsoluteTolerance(1.0);
		getPIDController().setToleranceBuffer(5);
		
	}


	@Override
	protected void initialize() {
		resetSampleTimer();
	}



	@Override
	protected void execute() { // TODO: Add manual aim
		Robot.shooter.shootLogic();
		Robot.drive.setAimDrop(true);

		SmartDashboard.putNumber("GyroSetPoint", getPIDController().getSetpoint());
		
		if (findSample) {
			if (Timer.getFPGATimestamp() > sampleTime) {
//				Robot.logger.logDebug("Finding new aim angle");
				calcNewSample();
			}
		}
		else if(Math.abs(SmartDashboard.getNumber("AIM_ERROR", 0.0)) < 1.0 && !finished) {
			resetSampleTimer();
		}
		
	}

	@Override
	protected boolean isFinished() {
		return finished || OI.coStick.getRawButton(2);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub	
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected double returnPIDInput() {
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
	}

	private void resetSampleTimer() {
		findSample = true;
		sampleTime = Timer.getFPGATimestamp() + 4;
	}
	
	private void calcNewSample() {
		findSample = false;
		samples++;
//		Robot.logger.logInfo("Aiming with sample " + samples);
		
		if(SmartDashboard.getNumber("BLOB_COUNT") > 0.0) {
			offsetAngle = SmartDashboard.getNumber("AIM_ERROR");
//			Robot.logger.logDebug("Setting gyro to " + RobotMap.gyro.getAngle() + offsetAngle + "\nCurrent angle is " + RobotMap.gyro.getAngle());
			setSetpoint(RobotMap.gyro.getAngle() + offsetAngle);
			
			if(Math.abs(SmartDashboard.getNumber("AIM_ERROR", 0.0)) < 1.0) {
				finished = true;
//				Robot.logger.logInfo("Robot is on target!");
			}
		}
		else {
			// TODO: Wait for blob instead of quitting
//			Robot.logger.logError("No blob found");
			finished = true;
		}
	}
	
}