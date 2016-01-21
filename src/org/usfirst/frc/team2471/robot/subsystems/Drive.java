package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	public static CANTalon left1;
	public static CANTalon left2;
	public static CANTalon left3;
	public static CANTalon right1;
	public static CANTalon right2;
	public static CANTalon right3;

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveLoop());
	}
	
	public Drive(){
		left1 = RobotMap.left1;
		left2 = RobotMap.left2;
		left3 = RobotMap.left3;
		right1 = RobotMap.right1;
		right2 = RobotMap.right2;
		right3 = RobotMap.right3;
	}
	

	public void setSpeed(double x, double y) {
		left1.set(x+y);
		left2.set(x+y);
		left3.set(x+y);
		right1.set(x-y);
		right2.set(x-y);
		right3.set(x-y);
	}

}
