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
		
		armRight.changeControlMode(TalonControlMode.Follower);
		armRight.set(armLeft.getDeviceID());
		armRight.reverseOutput(true);
		
/*		armLeft.changeControlMode(TalonControlMode.Current);
		armLeft.setF(0.025);
		armLeft.setPID(0,0,0);
		armLeft.enable();
*/	}
	
	public void rotateUp(){
		if((RobotMap.pdp.getCurrent(8) <= 12)){
			
			armLeft.set(0.5);
			//armLeft.setSetpoint(10000);
		}
	}
	
	public void rotateDown(){
		if((RobotMap.pdp.getCurrent(8) <= 12)){
			armLeft.set(-.5);
			//armLeft.setSetpoint(-10000);
		}
	}
	
	public void rotateStop(){
		armLeft.set(0.0);
		//armLeft.setSetpoint(0);
	}
	
	public void rotate( double power )
	{
		armLeft.set( power );
	}
}
