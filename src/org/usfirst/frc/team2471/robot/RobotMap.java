package org.usfirst.frc.team2471.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
/*_____________________________________Drive-------------------------------- */
    public static CANTalon left1;
    public static CANTalon left2;
    public static CANTalon left3;
    
    public static CANTalon right1;
    public static CANTalon right2;
    public static CANTalon right3;
    
/*_____________________________Launcher____________________________________ */
    public static Solenoid launcherPiston;
    public static DigitalInput launcherIsDown;
    public static AnalogInput launcherIsLocked, launcherPsiSensor; // TODO: better name
    
    
    
/*_____________________________Intake____________________________________ */
    
    public static CANTalon rollerIntake;
    
    public static void init(){
    	left1 = new CANTalon(0);
    	left2 = new CANTalon(1);
    	left3 = new CANTalon(2);
    	right1 = new CANTalon(3);
    	right2 = new CANTalon(4);
    	right3 = new CANTalon(5);
    	launcherPiston = new Solenoid(0); // TODO: Check
    	launcherIsDown = new DigitalInput(0);
    	launcherIsLocked = new AnalogInput(0);
    	launcherPsiSensor = new AnalogInput(1);
    	
    	
    	rollerIntake = new CANTalon(6);
    	
    }
}
