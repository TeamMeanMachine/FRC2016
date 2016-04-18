package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
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
	private boolean findSample;
	

	public Aim3(boolean finishOnTarget) { // Boolean so compiler doesn't complain
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
		if (findSample) {
			if (Timer.getFPGATimestamp() > sampleTime) {
				calcNewSample();
			}
		}
		else if(getPIDController().onTarget() && !finished) {
			resetSampleTimer();
		}
		
		Robot.shooter.shootLogic();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	private void resetSampleTimer() {
		findSample = true;
		sampleTime = Timer.getFPGATimestamp() + 4;
	}
	
	
	@Override
	protected double returnPIDInput() {
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
		
		
	}
	
	private void calcNewSample() {
		samples++;
		Robot.logger.logInfo("Aiming with sample " + samples);
		
		if(SmartDashboard.getNumber("BLOB_COUNT") > 0.0) {
			offsetAngle = SmartDashboard.getNumber("AIM_ERROR");
			
			if(getPIDController().onTarget()) { // If we are not in tolerance
				setSetpoint(RobotMap.gyro.getAngle() + offsetAngle);
			}
			else {
				finished = true;
			}
		}
		else {
			// TODO: Figure out what we want to do here
			Robot.logger.logError("No blob found");
			finished = true;
		}
	}
	
}
