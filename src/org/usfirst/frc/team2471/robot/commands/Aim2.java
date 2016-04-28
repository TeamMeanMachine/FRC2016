package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.util.CSVLogger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim2 extends PIDCommand {
	private PIDController aimController;
	private boolean targetFound;
	private int onTargetCount;
	private boolean finishOnTarget;
	
	private int onTargetMax;

	public Aim2(boolean finishOnTarget, boolean speedMode) {
		super(Constants.AIM_2_P, Constants.AIM_2_I, Constants.AIM_2_D);
		requires(Robot.drive);
		requires(Robot.shooter);

		aimController = getPIDController();
		SmartDashboard.putData("Aim2 PID", aimController);
		this.finishOnTarget = finishOnTarget;

		if(speedMode) {
			this.onTargetMax = 10;
		}
		else {
			this.onTargetMax = 50;
		}
	}
	
	public Aim2(boolean finishOnTarget) {
		this(finishOnTarget, false);
	}
	
	@Override
	public boolean isInterruptible() {
		return false;
	}

	@Override
	protected void initialize() {
		Robot.logger.logInfo("Aiming");
		targetFound = false;
		aimController.disable();
		onTargetCount = 0;

		// if (SmartDashboard.getBoolean("AutoAim") &&
		// !SmartDashboard.getBoolean("IntelVision")) {
		// RobotMap.vision.resume();
		// System.out.println("Vision Resumed");
		// }
	}

	@Override
	protected void execute() {
		if (SmartDashboard.getBoolean("AutoAim")) {
			if (SmartDashboard.getBoolean("IntelVision")) {
				if (!targetFound && SmartDashboard.getNumber("BLOB_COUNT", 0) > 0) {
					targetFound = true;
					aimController.enable();
				}
				SmartDashboard.putNumber("AimGyroError", aimController.getError());
				if (targetFound && Math.abs(RobotMap.gyro.getRate()) < 1) {
					double aimError = SmartDashboard.getNumber("AIM_ERROR");
					setSetpoint(RobotMap.gyro.getAngle() + aimError);
					
					double gyro = RobotMap.gyro.getAngle();
					Robot.logger.logInfo("Aiming to " + getSetpoint() + "\tGyro: " + gyro + "\tDifference: " + (getSetpoint() - gyro) + " degrees");
				}
				
				SmartDashboard.putNumber("GyroSetPoint", getPIDController().getSetpoint());
//				SmartDashboard.putNumber("Aim Error", aimController.getError());
				

				Robot.aimLogger.addToBuffer("Aim Error", SmartDashboard.getNumber("AIM_ERROR"));
				Robot.aimLogger.addToBuffer("Gyro Angle", RobotMap.gyro.getAngle());
				Robot.aimLogger.addToBuffer("Gyro Setpoint", aimController.getSetpoint());
				Robot.aimLogger.addToBuffer("Setpoint Error", aimController.getError());

				doRumble(true);

			} // end of intel vision
			else {
				SmartDashboard.putBoolean("Rumble", false);
				// if(!targetFound && RobotMap.vision.getBlobCount() > 0) {
				// targetFound = true;
				// aimController.enable();
				// }
				// if(targetFound) {
				// setSetpoint(RobotMap.vision.getGyroTarget());
				// }
				// if(Math.abs(aimController.getError()) < 0.5) {// &&
				// RobotMap.pressureSensor.getPressure() > 55.0) {
				// new RumbleJoystick(0.5, OI.coStick).start();
				// onTargetCount++;
				// }
				// SmartDashboard.putNumber("Aim Error",
				// aimController.getError());
			}
		} else {  // manual aim
			aimController.disable();
			double leftRightValue = OI.coStick.getRawAxis(4);
			double deadband = 0.2;
			if (Math.abs(leftRightValue) < deadband) { // dead band for xbox
				leftRightValue = 0.0;
			}
				
			else {
				leftRightValue = (leftRightValue - Math.signum(leftRightValue) * deadband) / (1.0 - deadband);
			}
			
			leftRightValue *= (leftRightValue * leftRightValue) * 0.6; // Ramp
				
			Robot.drive.setAimerMotor(leftRightValue);
			
			doRumble(false);
		}

		Robot.drive.setAimDrop(true);
//		Robot.shooter.shootLogic();
		
	}
	
	private void doRumble(boolean autoAim) {
		// Rumble stuff
		boolean rumbleHasPressure = RobotMap.pressureSensor.getPressure() > 52.0;
		boolean rumbleTopError = Math.abs(RobotMap.shootMotorTop.getError()) < 15.0;
		boolean rumbleBottomError = Math.abs(RobotMap.shootMotorBottom.getError()) < 15.0;
		boolean rumbleAimOnTarget;
		boolean rumbleHasBlob;
		if (autoAim) {
			rumbleAimOnTarget = Math.abs(SmartDashboard.getNumber("AIM_ERROR", 0.0)) < 1.0;
			rumbleHasBlob = SmartDashboard.getNumber("BLOB_COUNT", 0.0) > 0.0;
		}
		else {
			rumbleAimOnTarget = true;
			rumbleHasBlob = true;
		}
		SmartDashboard.putBoolean("RumbleHasBlob", rumbleHasBlob);
		SmartDashboard.putBoolean("RumbleAimOnTarget", rumbleAimOnTarget);
		SmartDashboard.putBoolean("RumbleHasPressure", rumbleHasPressure);
		SmartDashboard.putBoolean("RumbleTopError", rumbleTopError);
		SmartDashboard.putBoolean("RumbleBottomError", rumbleBottomError);

		boolean rumble = rumbleHasBlob && rumbleAimOnTarget && rumbleHasPressure && rumbleTopError && rumbleBottomError;
		SmartDashboard.putBoolean("Rumble", rumble);
		if (rumble) {
			new RumbleJoystick(0.5, OI.coStick).start();
			onTargetCount++;
		}
	}

	@Override
	protected boolean isFinished() {
		if (finishOnTarget) {
			return onTargetCount > onTargetMax;
		} else {
			return OI.coStick.getRawButton(2) || OI.coStick.getRawButton(6);
		}
	}

	@Override
	protected void end() {
		// Robot.drive.setAimDrop(false);
		aimController.disable();
		// RobotMap.vision.suspend();

		SmartDashboard.putBoolean("RumbleHasBlob", false);
		SmartDashboard.putBoolean("RumbleAimOnTarget", false);
		SmartDashboard.putBoolean("RumbleHasPressure", false);
		SmartDashboard.putBoolean("RumbleTopError", false);
		SmartDashboard.putBoolean("RumbleBottomError", false);
		SmartDashboard.putBoolean("Rumble", false);
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
//		Robot.logger.logDebug("Setting aimer to " + output);
		Robot.drive.setAimerMotor(output);
		// Robot.drive.setSpeed(-output, 0.0); // run the drivetrain instead
	}
}
