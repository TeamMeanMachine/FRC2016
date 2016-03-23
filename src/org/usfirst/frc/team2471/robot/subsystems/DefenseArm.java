 package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.RotateArm;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefenseArm extends PIDSubsystem{
	
	private CANTalon armMotor;
	private AnalogInput magnePot;
	private double targetAngle;
	
	
	public DefenseArm() {
		super(Constants.DEFENSE_P, Constants.DEFENSE_I, Constants.DEFENSE_D);
		armMotor = RobotMap.defenseArm;
		magnePot = RobotMap.magnepotArm;
//		armMotor.setVoltageRampRate(13.0);  // 13.0 volts per second
		setTargetAngle(getPosition());
		enable();		
	}

	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RotateArm());
	}
	
	public void rotate(double power) {
		if(RobotMap.pdp.getCurrent(8) <= 20 && RobotMap.pdp.getCurrent(7) <= 20) {
			armMotor.set(power);
		}
		else {
			setSetpoint(getPosition());
			armMotor.set(0.0);
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
	
	public double angleToVoltage(double angle) {
		double zeroVolts = SmartDashboard.getNumber("ArmZeroVolts", 2.314);
		angle /= (360.0/4.6);
		return angle + zeroVolts;
	}

	public double voltageToAngle(double voltage) {
		//What I think the conversion should be (according to the MagnePot datasheet)
		double zeroVolts = SmartDashboard.getNumber("ArmZeroVolts", 2.314) - 0.2; //Comp bot is 2.339 || Practice is 2.22
		voltage -= 0.2;
		voltage -= zeroVolts;
		double angle = voltage * (360.0/4.6);
		
		return angle;
	}

	public void setTargetAngle(double angle) {
		if (Robot.climbing) {
			targetAngle = SmartDashboard.getNumber("DefenseArmClimb", 107.0);
		}
		else {
			double maxAngle = SmartDashboard.getNumber("DefenseArmMax", 77.0);
			double minAngle = SmartDashboard.getNumber("DefenseArmMin", -13.0);
			
			if (angle > maxAngle) {
				angle = maxAngle;
			}
			else if (angle < minAngle) {
				angle = minAngle;
			}
			targetAngle = angle;
		}		
		setSetpoint(targetAngle);
	}
	
	public double getTargetError() {
		return targetAngle - getPosition();
	}
	
	public double getTargetAngle() {
		return targetAngle;
	}
}
