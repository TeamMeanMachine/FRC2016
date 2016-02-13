package org.usfirst.frc.team2471.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

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
    
    public static Encoder rightE;
    public static Encoder leftE;
    
/*_____________________________Shooter____________________________________ */
    public static CANTalon shootMotorTop, shootMotorBottom;
    
/*_________________________Aimer---------------------------------------*/
    public static CANTalon aimer;
    
    
    
/*_____________________________Intake____________________________________ */
    
    public static CANTalon rollerIntake;
    
/*______________________________Defense Arm_________________________________*/
    public static CANTalon portcullisArmLeft, portcullisArmRight;
    public static DigitalInput upperArmLimit;
    public static DigitalInput lowerArmLimit;

    
    public static void init(){
    	
    	aimer = new CANTalon(13);
    	//left1 = new CANTalon(0);
    	
    	left1 = new CANTalon(15);
    	left2 = new CANTalon(14);
    	left2.changeControlMode(TalonControlMode.Follower);
    	left2.set(left1.getDeviceID());
    	
    	right1 = new CANTalon(0);
    	right2 = new CANTalon(1);
    	right2.changeControlMode(TalonControlMode.Follower);
    	right2.set(right1.getDeviceID());
    	
    	/*
    	 * no wires
    	leftE = new Encoder(1, 2);
    	leftE.setDistancePerPulse( Math.PI * 7.5 / 200.0 / 12.0 )  // diameter, ticks per rev, inches per foot
    	rightE = new Encoder(3, 4);
    	rightE.setDistancePerPulse( -Math.PI * 7.5 / 200.0 / 12.0 )  // diameter, ticks per rev, inches per foot
    	*/
    	
    	shootMotorTop = new CANTalon(3);
    	shootMotorBottom = new CANTalon(2);
    	
    	rollerIntake = new CANTalon(9);
    	
    	portcullisArmLeft = new CANTalon(8);
    	portcullisArmRight = new CANTalon(7);
    	
    	/*
    	 * Do later
    	upperArmLimit = new DigitalInput(2);
    	lowerArmLimit = new DigitalInput(3);
    	 */
    }
}
