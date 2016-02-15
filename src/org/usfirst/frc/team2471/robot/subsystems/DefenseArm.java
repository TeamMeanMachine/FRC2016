package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.RotateArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DefenseArm extends Subsystem{
	
	public CANTalon armLeft;
	public CANTalon armRight;

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RotateArm());
	}
	
	
	public DefenseArm(){
		armLeft = RobotMap.defenseArmLeft;
		armRight = RobotMap.defenseArmRight;
		
		
		
/*		armLeft.changeControlMode(TalonControlMode.Current);
		armLeft.setF(0.025);
		armLeft.setPID(0,0,0);
		armLeft.enable();
*/	}
	
	
	public void rotateStop(){
		armLeft.set(0.0);
		//armLeft.setSetpoint(0);
	}
	
	public void rotate(double power)
	{
		armLeft.set(power);
	}
}
