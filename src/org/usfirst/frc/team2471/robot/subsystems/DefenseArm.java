package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.RotateArm;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefenseArm extends PIDSubsystem{
	
	public CANTalon armLeft;
	public CANTalon armRight;
	public AnalogInput magnePot;
	double targetAngle;
	
	public DefenseArm(double p, double i, double d) {
		super(p, i, d);
		armLeft = RobotMap.defenseArmLeft;
		magnePot = RobotMap.magnepotArm;
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
		//if((RobotMap.pdp.getCurrent(armLeft.getDeviceID() ) <= 12)){
			armLeft.set(power);
		//}
		/*else {
			armLeft.set(0.0);
		}*/
	}


	@Override
	protected double returnPIDInput() {
		double rValue = voltageToAngle( magnePot.getVoltage() );
		SmartDashboard.putNumber("Defense Arm Position", rValue );
		return rValue;
	}

	@Override
	protected void usePIDOutput(double output) {
		rotate( output );
	}
	
	static public double angleToVoltage(double angle) {
		angle /= 82.18;
		return angle + 2.314;
	}

	static public double voltageToAngle(double voltage) {
		voltage -= 2.314;
		return voltage * 82.18;
	}

	public void setTargetAngle(double angle) {
		targetAngle = angle;
		setSetpoint( targetAngle );
	}

	public double getTargetAngle() {
		return targetAngle;
	}
}
