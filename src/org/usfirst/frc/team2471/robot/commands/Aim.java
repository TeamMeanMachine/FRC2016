package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim extends PIDCommand {
//	private double p, i, d; Unused
	public static PIDController aimcontroller;
	
	public Aim() {
		super(Constants.AIM_P, Constants.AIM_I, Constants.AIM_D);
		requires(Robot.shooter);
		requires(Robot.drive);
		Robot.shooter.topMotor.set(0.2);
		setSetpoint(0.0 + SmartDashboard.getNumber("AimChange", 0.0));
		aimcontroller = getPIDController();
		SmartDashboard.putBoolean("Aim", true);
		SmartDashboard.putData("Aim PID", aimcontroller);
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
		}catch(Exception e){
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
	}

	@Override
	protected void execute() {
		try {
			setSetpoint(SmartDashboard.getNumber("AimChange"));
		}
		catch (Exception e) {
			setSetpoint(0.0);
			System.out.println("Could not find Dashboard variable from Intel Stick");
		}
		
		if (SmartDashboard.getBoolean("AutoAim")) {
			aimcontroller.enable();
		}
		else {
			aimcontroller.disable();
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
		//return onTarget();
		return OI.coStick.getRawButton(2);
	}

	@Override
	protected void end() {
		Robot.drive.setAimDrop(false);
		aimcontroller.disable();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}
}
