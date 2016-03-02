package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.Shoot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{
	
	private CANTalon topMotor;
	private CANTalon bottomMotor;
	private CANTalon intakeMotor;
	private PIDController topController;
	private PIDController bottomController;

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Shoot());
	}

	public Shooter() {
		
		topMotor = RobotMap.shootMotorTop;
		bottomMotor = RobotMap.shootMotorBottom;
		intakeMotor = RobotMap.shootIntake;
		
		topController = new PIDController(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D, 0, new topSource(), new topOutput());
		bottomController = new PIDController(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D, 0, new bottomSource(), new bottomOutput());
		
		SmartDashboard.putData("Top PID", topController);
		SmartDashboard.putData("Bot PID", bottomController);
		
		topMotor.reverseSensor(false);
		bottomMotor.reverseSensor(true);

		topMotor.configEncoderCodesPerRev( 250 );
		bottomMotor.configEncoderCodesPerRev( 250 );
		
		bottomMotor.setInverted(true);  // this one is for talon SRX open loop
		
		/*
		bottomMotor.reverseOutput(true);  // talon SRX closed loop

		topMotor.changeControlMode( CANTalon.TalonControlMode.Speed );
		bottomMotor.changeControlMode( CANTalon.TalonControlMode.Speed );
		
		topMotor.setFeedbackDevice( CANTalon.FeedbackDevice.QuadEncoder );
		bottomMotor.setFeedbackDevice( CANTalon.FeedbackDevice.QuadEncoder );
		
		topMotor.configPeakOutputVoltage( 12.0, 0.0 );
		bottomMotor.configPeakOutputVoltage( 0.0, -12.0 );
		
		topMotor.configNominalOutputVoltage( 0.0, 0.0 );
		bottomMotor.configNominalOutputVoltage( 0.0, 0.0 );
		
		topMotor.setPID( 2000, 0, 0.0);
		bottomMotor.setPID( 2000, 0, 0.0);
		
		topMotor.setF( 0.0 );
		bottomMotor.setF( 0.0 );*/
	}
	
	
	class bottomSource implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return bottomMotor.getPIDSourceType();
		}

		@Override
		public double pidGet() {
			return -bottomMotor.getEncVelocity();
		}
		
	}
	
	class topSource implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return topMotor.getPIDSourceType();
		}

		@Override
		public double pidGet() {
			return topMotor.getEncVelocity();
		}
		
	}
	
	class bottomOutput implements PIDOutput{
		@Override
		public void pidWrite(double output) {
			bottomMotor.set(-bottomMotor.get()+output);
		}
	} 
	
	class topOutput implements PIDOutput{
		@Override
		public void pidWrite(double output) {
			topMotor.set(topMotor.get()+output);
		}
	}
	
	public void shoot(double topSpeed, double bottomSpeed) {
		topController.setSetpoint(topSpeed);
		bottomController.setSetpoint(bottomSpeed);
		
		topController.enable();
		bottomController.enable();

		if (Math.abs(topMotor.getEncVelocity()) > 1000 && Math.abs(bottomMotor.getEncVelocity()) > 1000) {
			intakeMotor.set(-0.80);
		}else {
			intakeMotor.set(0.0);
		}
	}

	public void stop() {
		topController.disable();
		bottomController.disable();
		topMotor.set(0.0);
		bottomMotor.set(0.0);
		intakeMotor.set(0.0);
	}

	public void shootLogic() {
		double topSpeed = SmartDashboard.getNumber("TopSetSpeed", 2700);
		double bottomSpeed = SmartDashboard.getNumber("BottomSetSpeed", 2500);
		
		if (SmartDashboard.getBoolean("ShooterEnable"))
		{
			Robot.shooter.shoot(topSpeed, bottomSpeed);
			RobotMap.ringLight.set(true);
		}
		else
		{
			Robot.shooter.stop();
			RobotMap.ringLight.set(false);
		}
		
		SmartDashboard.putNumber("TopSpeed", topMotor.getEncVelocity());
		SmartDashboard.putNumber("BottomSpeed", -bottomMotor.getEncVelocity());
		SmartDashboard.putNumber("Top Error", topController.getError());
		SmartDashboard.putNumber("Bottom Error", bottomController.getError());
	}
}
