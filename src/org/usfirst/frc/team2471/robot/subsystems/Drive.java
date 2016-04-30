package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commandgroups.ArmClimb;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	private CANTalon leftDrive;
	private CANTalon rightDrive;
	
	private CANTalon aimer;
	
	private CANTalon liftExtension;
	
	private Solenoid aimDropCylinder;
	
	public PIDController turnRateController;
	public PIDController turnAngleController;
	
	private double turnResult = 0.0;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	public Drive(){
		leftDrive = RobotMap.leftDrive;
		rightDrive = RobotMap.rightDrive;
		aimer = RobotMap.aimer;
		
		liftExtension = RobotMap.liftExtension;
		
		aimDropCylinder = RobotMap.aimDropCylinder;
		
		turnRateController = new PIDController( Constants.TURN_P, Constants.TURN_I_TELEOP, Constants.TURN_D, new turnRatePIDSource(), new turnRatePIDOutput());
		turnRateController.setOutputRange(-1.0, 1.0);
		
		SmartDashboard.putData("TurnRatePID", turnRateController);
	}
	
	class turnRatePIDSource implements PIDSource {
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return RobotMap.gyro.getPIDSourceType();
		}

		@Override
		public double pidGet() {
			return -RobotMap.gyro.getRate();
		}
	}
	
	class turnRatePIDOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			turnResult += output;
			if(turnResult > 1.0) {
				turnResult = 1.0;
			}
			else if(turnResult < -1.0) {
				turnResult = -1.0;
			}
		}
	}
	
	public void autonomousMode() {
		turnRateController.setPID(Constants.TURN_P, Constants.TURN_I_AUTO, Constants.TURN_D);
		Robot.logger.logDebug("Using Auto Mode PID for drive");
	}
	
	public void teleopMode() {
		turnRateController.setPID(Constants.TURN_P, Constants.TURN_I_TELEOP, Constants.TURN_D);
		Robot.logger.logDebug("Using Telop Mode PID for drive");
	}
	
	public void setAimerMotor(double power) {
		aimer.set(power);
	}
	
	public void setAimDrop(boolean value) {
		aimDropCylinder.set(value);
	}
	public boolean getAimDropStatus() {
		return aimDropCylinder.get();
	}
	
	public void setPower(double right, double forward){
		
		
		//Robot.drive.autonomousMode();
		//Robot.drive.turnRateController.enable();
		//Robot.drive.turnRateController.setSetpoint(right * FASTRATE);
		
		//right = Robot.drive.getTurnResult();
		
		rightDrive.set( forward + right );
		leftDrive.set( -(forward - right) );
		
//		SmartDashboard.putNumber("Accel X", RobotMap.accelerometer.getX());
//		SmartDashboard.putNumber("Accel Y", RobotMap.accelerometer.getY());
//		SmartDashboard.putNumber("Accel Z", RobotMap.accelerometer.getZ());
		
		/*SmartDashboard.putNumber( "RightRear", RobotMap.pdp.getCurrent(0) );
		SmartDashboard.putNumber( "RightFront", RobotMap.pdp.getCurrent(1) );
		SmartDashboard.putNumber( "LeftFront", RobotMap.pdp.getCurrent(14) );
		SmartDashboard.putNumber( "LeftRear", RobotMap.pdp.getCurrent(15) );
		SmartDashboard.putNumber( "TotalCurrent", RobotMap.pdp.getCurrent(0)+RobotMap.pdp.getCurrent(1)+RobotMap.pdp.getCurrent(14)+RobotMap.pdp.getCurrent(15));*/
	}
	
	public void setLiftExtension(double power) {
		liftExtension.set(power);
	}

	public double getTurnResult() {
		return turnResult;
	}

	public void setTurnResult(double turnResult) {
		this.turnResult = turnResult;
	}
	
	public double getEncoderDistance() {
		return ( rightDrive.getPosition() - leftDrive.getPosition() ) / 2.0;
	}
	
	public void useControllerInput(double forward, double turn) {
		double deadband = 0.20;
		if (turn <= deadband && turn >= -deadband){
			turn = 0;
		}
		else {
			turn = (turn - Math.signum(turn)*deadband) / (1.0-deadband);
		}
		
		if(forward <= deadband && forward >= -deadband){
			forward = 0;
		}
		else {
			forward = (forward - Math.signum(forward)*deadband) / (1.0-deadband);
		}
		
		//No cubic functions for now, but possibly later
		//x = x * x * x;
		//y = y * y * y;
		
		boolean useGyro = SmartDashboard.getBoolean("UseGyro", false);
		
		final double FASTRATE = 250;  // TODO: determine how fast is fast
		
		if (useGyro) {
			Robot.drive.teleopMode();
			Robot.drive.turnRateController.enable();
			
			Robot.drive.turnRateController.setSetpoint(turn * FASTRATE);
			
			turn = Robot.drive.getTurnResult();
		}
		else
		{
			Robot.drive.turnRateController.disable();
		}
		
		SmartDashboard.putNumber("DriveDistance", Robot.drive.getEncoderDistance());
		
		if(!Robot.drive.getAimDropStatus()) {
			// Climb stuff
			double liftPower = OI.coStick.getRawAxis(3);
			if(Math.abs(liftPower) < 0.075) {
				liftPower = 0;
				RobotMap.pto.set(false);
			}
			else { // climbing
				RobotMap.pto.set(true);
				RobotMap.ratchet.set(false);
				turn = 0;
				forward = 0;
				Robot.climbing = true;
			}
			Robot.drive.setPower(turn, forward - liftPower);  // using the climbing trigger is the same as driving backwards.

			// Climb extension stuff
			double extendPower = -OI.coStick.getRawAxis(2);
			if(Math.abs(extendPower) < 0.075) {
				extendPower = 0;
			}
			else {
				RobotMap.ratchet.set(true);
				if (!Robot.climbing){
					Robot.climbing = true;
					new ArmClimb().start();
				}
			}
			Robot.drive.setLiftExtension(extendPower * 0.75);
		}
	}

	public void autoDrive(double turn, double forward) {
		
		//No cubic functions for now, but possibly later
		//x = x * x * x;
		//y = y * y * y;
		
		boolean useGyro = SmartDashboard.getBoolean("UseGyro", false);
		
		final double FASTRATE = 250;  // TODO: determine how fast is fast
		
		if (useGyro) {
			Robot.drive.autonomousMode();
			Robot.drive.turnRateController.enable();
			
			Robot.drive.turnRateController.setSetpoint(turn * FASTRATE);
			
			turn = Robot.drive.getTurnResult();
		}
		else
		{
			Robot.drive.turnRateController.disable();
		}
		
		SmartDashboard.putNumber("DriveDistance", Robot.drive.getEncoderDistance());
		

		if(!Robot.drive.getAimDropStatus()) {
			// Climb stuff
			double liftPower = OI.coStick.getRawAxis(3);
			if(Math.abs(liftPower) < 0.075) {
				liftPower = 0;
				RobotMap.pto.set(false);
			}
			else { // climbing
				RobotMap.pto.set(true);
				RobotMap.ratchet.set(false);
				turn = 0;
				forward = 0;
				Robot.climbing = true;
			}
			Robot.drive.setPower(turn, forward - liftPower);  // using the climbing trigger is the same as driving backwards.
		}
	}
	
	public void resetEncoders() {
		leftDrive.setEncPosition(0);
		rightDrive.setEncPosition(0);
		leftDrive.setPosition(0);
		rightDrive.setPosition(0);
	}
}
