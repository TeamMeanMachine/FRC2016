package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{
	
	private CANTalon topMotor;
	private CANTalon bottomMotor;
	private CANTalon intakeMotor;
	private PIDController topController;
	private PIDController bottomController;
	private DigitalInput ballSensor;
	private boolean shooterOn = false;
	private Solenoid flashlight;
	private Solenoid ringLight;

//	private Thread shootLogic;
	
	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new Shoot());
	}

	public Shooter() {
		
		topMotor = RobotMap.shootMotorTop;
		bottomMotor = RobotMap.shootMotorBottom;
		intakeMotor = RobotMap.shootIntake;
		ballSensor = RobotMap.shooterBallSensor;
		
		topController = new PIDController(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D, 0, new topSource(), new topOutput());
		bottomController = new PIDController(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D, 0, new bottomSource(), new bottomOutput());
		
		SmartDashboard.putData("Top PID", topController);
		SmartDashboard.putData("Bot PID", bottomController);
		
		topMotor.reverseSensor(false);
		bottomMotor.reverseSensor(false);  // see if this fixes the bottom motor

		topMotor.configEncoderCodesPerRev( 250 );
		bottomMotor.configEncoderCodesPerRev( 250 );
		
		bottomMotor.setInverted(true);  // this one is for talon SRX open loop
		
		flashlight = RobotMap.flashlight;
		ringLight = RobotMap.ringLight;
		
//		shootLogic = new Thread(new ShootLogic());
//		shootLogic.start();
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
			return -bottomMotor.getEncVelocity();  //MAKE THISS NEGATIVE FOR COMPETITION BOT
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
			bottomMotor.set(-bottomMotor.get()+output);  // bottomMotor is negated because despite the fact that the talon is reversed, retrieving power doesn't observe this fact
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
		//topMotor.set(.5);
	}

	public void stop() {
		topController.disable();
		bottomController.disable();
		topMotor.set(0.0);
		bottomMotor.set(0.0);
		intakeMotor.set(0.0);
		shooterOn = false;
		setLights(false);
		Robot.drive.setAimDrop(false);
	}
	
	public void start() {
		shooterOn = true;
	}
	
	public boolean getShooterState() {
		return shooterOn;
	}

	
	
	public void shooterIntakeOn() {
		if(Math.abs(topController.getError()) < 500 && Math.abs(bottomController.getError()) < 500)
			intakeMotor.set(-0.6);
	}
	
	public void queueIntake() {
		intakeMotor.set(-0.5);
	}
	
	public void variableQueue(double power){
		intakeMotor.set(-power);
	}
	
	public void shooterIntakeOff() {
		System.out.println("Intake Off2");
		intakeMotor.set(0.0);
	}
	
	public void shooterIntakeReverse(){
		intakeMotor.set(1);
	}
	
	public boolean hasBall() {
		return ballSensor.get();
	}
	
	public void setFlashlight(boolean state) {
		flashlight.set(false);
	}

	public void setLights(boolean state) {
		flashlight.set(false);
		if(ringLight.get() != state) {
			ringLight.set(state);
		}
	}
	
	public void shootLogic() {
		double topSpeed = SmartDashboard.getNumber("TopSetSpeed", 2700);
		double bottomSpeed = SmartDashboard.getNumber("BottomSetSpeed", 2500);
		
		if (shooterOn) {
			shoot(topSpeed, bottomSpeed);
			setLights(true);
		}
		else {
			stop();
		}
		SmartDashboard.putNumber("TopSpeed", topMotor.getEncVelocity());
		SmartDashboard.putNumber("BottomSpeed", -bottomMotor.getEncVelocity());
		SmartDashboard.putNumber("Top Error", topController.getError());
		SmartDashboard.putNumber("Bottom Error", bottomController.getError());
		SmartDashboard.putNumber("ShooterSpeedDiff", topMotor.getSetpoint() - bottomMotor.getSetpoint());
		
	}
	
//	private class ShootLogic implements Runnable {
//		@Override
//		public void run() {
//			while(true) {
//				double topSpeed = SmartDashboard.getNumber("TopSetSpeed", 2700);
//				double bottomSpeed = SmartDashboard.getNumber("BottomSetSpeed", 2500);
//				
//				if (shooterOn) {
//					shoot(topSpeed, bottomSpeed);
//					setLights(true);
//				}
//				else {
//					stop();
//				}
//				SmartDashboard.putNumber("TopSpeed", topMotor.getEncVelocity());
//				SmartDashboard.putNumber("BottomSpeed", -bottomMotor.getEncVelocity());
//				SmartDashboard.putNumber("Top Error", topController.getError());
//				SmartDashboard.putNumber("Bottom Error", bottomController.getError());
//				SmartDashboard.putNumber("ShooterSpeedDiff", topMotor.getSetpoint() - bottomMotor.getSetpoint());
//				
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					Robot.logger.logError("ShootLogic thread interrupted!");
//				}
//			}
//		}
//	}
}

