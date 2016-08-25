package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{
	
	private CANTalon topMotor;
	private CANTalon bottomMotor;
	private CANTalon intakeMotor;
	private DigitalInput ballSensor;
	private boolean shooterOn = false;
	private Solenoid flashlight;
	private Solenoid ringLight;
	
	@Override
	protected void initDefaultCommand() {}

	public Shooter() {
		
		topMotor = RobotMap.shootMotorTop;
		bottomMotor = RobotMap.shootMotorBottom;
		intakeMotor = RobotMap.shootIntake;
		ballSensor = RobotMap.shooterBallSensor;

		topMotor.configEncoderCodesPerRev( 250 );
		bottomMotor.configEncoderCodesPerRev( 250 );
		
		bottomMotor.setInverted(true);  // this one is for talon SRX open loop
		
		flashlight = RobotMap.flashlight;
		ringLight = RobotMap.ringLight;
		
//		shootLogic = new Thread(new ShootLogic());
//		shootLogic.start();
	}
	
	public void shoot(double topSpeed, double bottomSpeed) {
		topMotor.set(topSpeed);
		bottomMotor.set(bottomSpeed);
	}

	public void stop() {
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
		double topSpeed = SmartDashboard.getNumber("TopSetSpeed", 0.5);
		double bottomSpeed = SmartDashboard.getNumber("BottomSetSpeed", 0.5);
		
		if (shooterOn) {
			shoot(topSpeed, bottomSpeed);
			setLights(true);
		}
		else {
			stop();
		}		
	}
}

