package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveWithHeading extends DriveDistanceCommand {
	private double headingAngle;
	
	public DriveWithHeading(double distance, double speed, double headingAngle) {
		super(distance, 0, speed);
		this.headingAngle = headingAngle;
		rotateController = new PIDController(Constants.ROTATE_P, Constants.ROTATE_I, Constants.ROTATE_D, 
				new RotateSource(), new RotateOutput());
	}
	PIDController rotateController;

	@Override
	protected void initialize() {
		super.initialize(); // In case we add something later
		Robot.logger.logDebug("Starting angle: " + RobotMap.gyro.getAngle());
		rotateController.setSetpoint(headingAngle);
		rotateController.enable();
	}
	
	private class RotateSource implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return RobotMap.gyro.getPIDSourceType();
		}

		@Override
		public double pidGet() {
			return RobotMap.gyro.getAngle();
		}
	}
	private class RotateOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			Robot.logger.logDebug("Modifying turn by " + output);
			x = -output;
		}
	}
	

}
