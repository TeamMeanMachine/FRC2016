/**
 * 
 */
package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.LaunchLoop;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem{
	public static boolean launching = false;
	
	Solenoid launcherPiston;
    public static DigitalInput launcherIsDown;
    public static AnalogInput launcherIsLocked, launcherPsiSensor;

	protected void initDefaultCommand() {
		setDefaultCommand(new LaunchLoop());
	}

	public Catapult(){
		launcherPiston = RobotMap.launcherPiston;
		launcherIsDown = RobotMap.launcherIsDown;
		launcherIsLocked = RobotMap.launcherIsLocked;
		launcherPsiSensor = RobotMap.launcherPsiSensor;
	}
	
	public void goDown(){
		launcherPiston.set(false);
	}

}
