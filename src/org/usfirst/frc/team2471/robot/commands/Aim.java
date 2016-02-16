package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim extends PIDCommand {
//	private double p, i, d; Unused
	public static PIDController aimcontroller;
	
	public Aim() {
		super(0.01, 0.0, 0.0);
		Robot.shooter.motor1.set(0.2);
		setSetpoint(0.0 + SmartDashboard.getNumber("AimChange", 0.0));
		aimcontroller = getPIDController();
		SmartDashboard.putBoolean("Aim", true);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double returnPIDInput() {
		double error;
		double blobCount = SmartDashboard.getNumber("BLOB_COUNT", -1.0d);
//		if (blobCount == -1.0d) {
//			System.out.println("Connection to compute stick failed");
//			error = -100;
//		}
		if (blobCount > 0) {
			return SmartDashboard.getNumber("AIM_ERROR");
		}
		else {
			error = 0.0;
		}
		SmartDashboard.putNumber("Error", error);
		return error;
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
	}

	@Override
	protected void initialize() {
		Robot.drive.setAimDrop(true);
		Shoot.x = .7;
		Shoot.y = -.5;
		aimcontroller.enable();
	}

	@Override
	protected void execute() {
		setSetpoint(0.0 + SmartDashboard.getNumber("Aim Change"));
	}

	@Override
	protected boolean isFinished() {
		//return Math.abs(returnPIDInput()) <= 3;
		return OI.driverStick.getRawButton(2);
	}

	@Override
	protected void end() {
		Robot.drive.setAimDrop(false);
		aimcontroller.disable();
		SmartDashboard.putBoolean("Aim", true);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
