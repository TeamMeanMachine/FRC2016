package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.RotateArm;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefenseArm extends PIDSubsystem{
	
	public CANTalon armLeft;
	public CANTalon armRight;
	public AnalogInput magnePot;
	double targetAngle;
	int smartDashboardBuffer = 0;
	
	public DefenseArm(double p, double i, double d) {
		super(p, i, d);
		armLeft = RobotMap.defenseArmLeft;
		magnePot = RobotMap.magnepotArm;
		setTargetAngle(getPosition());
		
		enable();		
	}

	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RotateArm());
	}
	
	
	public void rotateStop(){
		//armLeft.set(0.0);
		armLeft.setSetpoint(0);
	}
	
	public void rotate(double power)
	{
		if(RobotMap.pdp.getCurrent(8) <= 20 && RobotMap.pdp.getCurrent(7) <= 20) {
			armLeft.set(power);
		}
		else {
			setSetpoint(getPosition());
			armLeft.set(0.0);
		}
	}


	@Override
	protected double returnPIDInput() {
		double rValue = voltageToAngle( magnePot.getVoltage() );
		return rValue;
	}

	@Override
	protected void usePIDOutput(double output) {
		rotate( output );
	}
	
	static public double angleToVoltage(double angle) {
		double zeroVolts = SmartDashboard.getNumber("ArmZeroVolts", 2.314);
		angle /= 82.18;
		return angle + zeroVolts;
	}

	static public double voltageToAngle(double voltage) {
		double zeroVolts = SmartDashboard.getNumber("ArmZeroVolts", 2.314);
		voltage -= zeroVolts;
		return voltage * 82.18;
	}

	public void setTargetAngle(double angle) {
		if (angle > 62) {
			angle = 62;
		}
		else if (angle < -13) {
			angle = -13;
		}
		setSetpoint(angle);
		

		smartDashboardBuffer++;
		if(smartDashboardBuffer % 20 == 0) { // To prevent overloading the network tables
			SmartDashboard.putNumber("Defense Arm Position", angle);
			SmartDashboard.putNumber("Defense Arm Error", getPIDController().getError());
		}
	}

	public double getTargetAngle() {
		return targetAngle;
	}
}
