package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim extends PIDCommand {
	private PIDController aimController;
	private boolean finishOnTarget;
	private int onTargetCount;
	
	public Aim(boolean _finishOnTarget) {
		super(Constants.AIM_P, Constants.AIM_I, Constants.AIM_D);

		requires(Robot.shooter);
		requires(Robot.drive);

		setSetpoint(SmartDashboard.getNumber("AimChange", 0.0));
		aimController = getPIDController();
		SmartDashboard.putData("Aim PID", aimController);
		finishOnTarget = _finishOnTarget;
	}

	@Override
	protected double returnPIDInput() {
		double input;
		try{
			double blobCount = SmartDashboard.getNumber("BLOB_COUNT", -1.0d);
			if (blobCount == -1.0d) {
				System.out.println("Connection to compute stick failed");
				input = -100;
			}
			if (blobCount > 0) {
				return SmartDashboard.getNumber("AIM_ERROR");
			}
			else {
				input = getSetpoint();
			}
			SmartDashboard.putNumber("Error", input);
			return input;
		} catch(Exception e){
			System.out.println("Could not find Dashboard variable from Intel Stick");
			return -100;
		}
		
		//return getSetpoint();  // no aiming for now...
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
	}

	@Override
	protected void initialize() {
		Robot.drive.setAimDrop(true);
		System.out.println("Aim");
		onTargetCount = 0;
	}

	@Override
	protected void execute() {
		try {
			setSetpoint(SmartDashboard.getNumber("AimChange"));
		} catch (Exception e) {
			setSetpoint(0.0);
			System.out.println("Could not find Dashboard variable from Intel Stick");
		}
		
		if (SmartDashboard.getBoolean("AutoAim")) {
			aimController.enable();
			if(Math.abs(aimController.getError()) < 6.0) {
				onTargetCount++;
				new RumbleJoystick(0.25, OI.coStick).start();
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
}
