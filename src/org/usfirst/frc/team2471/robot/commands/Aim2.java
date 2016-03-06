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
	}

	@Override
	protected void execute() {		
		if (SmartDashboard.getBoolean("AutoAim")) {
			if(!targetFound && SmartDashboard.getNumber("BLOB_COUNT") > 0) {
				targetFound = true;
				setSetpoint(SmartDashboard.getNumber("GYRO_TARGET"));
				Robot.drive.setAimDrop(true);
				aimController.enable();
			}
			if(Math.abs(aimController.getError()) < 0.5) {
				new RumbleJoystick(0.5, OI.coStick).start();
				onTargetCount++;
			}
		}
		else {
			aimController.disable();
			double leftRightValue = OI.coStick.getRawAxis(4);
			if(Math.abs(leftRightValue) <= 0.05) {
				leftRightValue = 0;
			}
			Robot.drive.setAimerMotor(leftRightValue);
		}
		
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
		Robot.drive.setAimDrop(false);
		aimController.disable();
	}

	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected double returnPIDInput() {
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
	}
}
