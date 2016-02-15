package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim extends PIDCommand {
	public static double p, i, d;
	
	public Aim() {
		super(0.02, 0.0, 0.0);
		Robot.shooter.motor1.set(0.2);
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
		SmartDashboard.putNumber("PID", error);
		return error;
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
	}

	@Override
	protected void initialize() {
		Robot.drive.setAimDrop(true);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(returnPIDInput()) <= 3;
	}

	@Override
	protected void end() {
		Robot.drive.setAimDrop(false);		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
