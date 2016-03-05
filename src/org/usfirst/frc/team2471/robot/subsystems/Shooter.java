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
	private boolean shooterOn = false;

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new Shoot());
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
	}

	public void stop() {
		topController.disable();
		bottomController.disable();
		topMotor.set(0.0);
		bottomMotor.set(0.0);
		intakeMotor.set(0.0);
		shooterOn = false;
		RobotMap.ringLight.set(false);
		Robot.drive.setAimDrop(false);
	}
	
	public void start() {
		shooterOn = true;
	}
	
	public boolean getShooterState() {
		return shooterOn;
	}

	public void shootLogic() {
		double topSpeed = SmartDashboard.getNumber("TopSetSpeed", 2700);
		double bottomSpeed = SmartDashboard.getNumber("BottomSetSpeed", 2500);
		
		if (shooterOn)
		{
			shoot(topSpeed, bottomSpeed);
			RobotMap.ringLight.set(true);
		}
		else
		{
			stop();
		}
		
		SmartDashboard.putNumber("TopSpeed", topMotor.getEncVelocity());
		SmartDashboard.putNumber("BottomSpeed", -bottomMotor.getEncVelocity());
		SmartDashboard.putNumber("Top Error", topController.getError());
		SmartDashboard.putNumber("Bottom Error", bottomController.getError());
	}
	
	public void shooterIntakeOn() {
		if(Math.abs(topController.getError()) < 500 && Math.abs(bottomController.getError()) < 500)
			intakeMotor.set(-0.8);
	}
	
	public void shooterIntakeOff() {
		System.out.println("Intake Off2");
		intakeMotor.set(0.0);
	}
}
