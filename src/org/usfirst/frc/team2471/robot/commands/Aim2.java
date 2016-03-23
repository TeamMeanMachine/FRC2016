package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim2 extends PIDCommand {
	private PIDController aimController;
	private boolean targetFound;
	private int onTargetCount;
	private boolean finishOnTarget;
	
	public Aim2(boolean _finishOnTarget) {
		super(Constants.AIM_ANGLE_P, Constants.AIM_ANGLE_I, Constants.AIM_ANGLE_D);
		requires(Robot.drive);
		requires(Robot.shooter);
		
		aimController = getPIDController();
		SmartDashboard.putData("Aim2 PID", aimController);
		finishOnTarget = _finishOnTarget;
	}

	@Override
	protected void initialize() {
		targetFound = false;
		aimController.disable();
		onTargetCount = 0;
		
		if (SmartDashboard.getBoolean("AutoAim") && !SmartDashboard.getBoolean("IntelVision")) {
			RobotMap.vision.startVision();
		}
	}

	@Override
	protected void execute() {		
		if (SmartDashboard.getBoolean("AutoAim")) {
			if(SmartDashboard.getBoolean("IntelVision")) {
				if(!targetFound && SmartDashboard.getNumber("BLOB_COUNT",0) > 0) {
					targetFound = true;
					aimController.enable();
				}
				if(targetFound) {
					setSetpoint(SmartDashboard.getNumber("GYRO_TARGET",0));
				}
				if(Math.abs(aimController.getError()) < 0.5) {// && RobotMap.pressureSensor.getPressure() > 55.0) {
				if(Math.abs(aimController.getError()) < 0.5 && RobotMap.pressureSensor.getPressure() > 55.0) {
					new RumbleJoystick(0.5, OI.coStick).start();
					onTargetCount++;
				}
				SmartDashboard.putNumber("Aim Error", aimController.getError());
			}
			else {
				if(!targetFound && RobotMap.vision.getBlobCount() > 0) {
					targetFound = true;
					aimController.enable();
				}
				if(targetFound) {
					setSetpoint(RobotMap.vision.getGyroTarget());
				}
				if(Math.abs(aimController.getError()) < 0.5) {// && RobotMap.pressureSensor.getPressure() > 55.0) {
					new RumbleJoystick(0.5, OI.coStick).start();
					onTargetCount++;
				}
				SmartDashboard.putNumber("Aim Error", aimController.getError());
			}
		}
		else {
			aimController.disable();
			double leftRightValue = OI.coStick.getRawAxis(4);
			double deadband = 0.2;
			if (Math.abs(leftRightValue) < deadband)  // dead band for xbox
				leftRightValue = 0.0;
			else
				leftRightValue = (leftRightValue - Math.signum(leftRightValue)*deadband) / (1.0-deadband);
			Robot.drive.setAimerMotor(leftRightValue);
		}

		Robot.drive.setAimDrop(true);
		Robot.shooter.shootLogic();
	}

	@Override
	protected boolean isFinished() {
		if(finishOnTarget) {
			return onTargetCount > 50.0;
		}
		else {
			return OI.coStick.getRawButton(2);
		}
	}

	@Override
	protected void end() {
//		Robot.drive.setAimDrop(false);
		aimController.disable();
		RobotMap.vision.stopVision();
	}

	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected double returnPIDInput() {
		return RobotMap.navx.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
		//Robot.drive.setSpeed(-output, 0.0);  // run the drivetrain instead
	}
}
