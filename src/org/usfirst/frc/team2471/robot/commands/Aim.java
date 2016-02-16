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
		super(0.005, 0.0, 0.01);
		requires( Robot.shooter );
		Robot.shooter.motor1.set(0.2);
		setSetpoint(0.0 + SmartDashboard.getNumber("AimChange", 0.0));
		aimcontroller = getPIDController();
		SmartDashboard.putBoolean("Aim", true);
	}

	@Override
	protected double returnPIDInput() {
		double input;
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
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
	}

	@Override
	protected void initialize() {
		Robot.drive.setAimDrop(true);
		aimcontroller.enable();
		System.out.println("Aim");
	}

	@Override
	protected void execute() {
		setSetpoint(0.0 + SmartDashboard.getNumber("Aim Change"));
		Robot.shooter.shootLogic();
	}

	@Override
	protected boolean isFinished() {
		//return onTarget();
		return OI.coStick.getRawButton(3);
	}

	@Override
	protected void end() {
		Robot.drive.setAimDrop(false);
		aimcontroller.disable();
		//SmartDashboard.putBoolean("Aim", true);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}
}
