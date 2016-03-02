package org.usfirst.frc.team2471.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static PowerDistributionPanel pdp;
	
/*_____________________________________Drive-------------------------------- */
    public static CANTalon left1;
    public static CANTalon left2;
    
    public static CANTalon right1;
    public static CANTalon right2;

    public static CANTalon liftExtension;
    
    public static Solenoid aimDropCylinder;
    public static Solenoid pto;

    
/*_____________________________Shooter____________________________________ */
    public static CANTalon shootMotorTop, shootMotorBottom, shootIntake;
    
/*_________________________Aimer---------------------------------------*/
    public static CANTalon aimer;
    public static Encoder aimEncoder;
    
    
    
/*_____________________________Intake____________________________________ */
    
    public static CANTalon rollerIntake;
    
/*______________________________Defense Arm_________________________________*/
    public static CANTalon defenseArmLeft, defenseArmRight;
    public static DigitalInput upperArmLimit;
    public static DigitalInput lowerArmLimit;
    
    public static Solenoid intakeActuate;
    public static Solenoid ringLight;
    
    
    public static AnalogInput magnepotArm;
    
    public static DigitalInput ballInSensor;
    public static AnalogGyro gyro; 
    public static BuiltInAccelerometer accelerometer;
    
    public static void init(){
    	
    	aimer = new CANTalon(13);
    	//left1 = new CANTalon(0);
    	
    	left1 = new CANTalon(15);
    	//Encoder 2.1 times wheel
    	//24.178 in per wheel rev
    	//0.49 rev per ft
    	
    	left2 = new CANTalon(14);
    	left2.changeControlMode(TalonControlMode.Follower);
    	left2.set(left1.getDeviceID());
    	left2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	left2.configEncoderCodesPerRev(250);
    	
    	right1 = new CANTalon(0);
    	
    	right2 = new CANTalon(1);
    	right2.changeControlMode(TalonControlMode.Follower);
    	right2.set(right1.getDeviceID());
    	right2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	right2.configEncoderCodesPerRev(250);
    	
    	aimDropCylinder = new Solenoid(4);
    	pto = new Solenoid(5);
    	
    	liftExtension = new CANTalon(12);
    	
    	/*
    	 * no wires
    	leftE = new Encoder(1, 2);
    	leftE.setDistancePerPulse( Math.PI * 7.5 / 200.0 / 12.0 )  // diameter, ticks per rev, inches per foot
    	rightE = new Encoder(3, 4);
    	rightE.setDistancePerPulse( -Math.PI * 7.5 / 200.0 / 12.0 )  // diameter, ticks per rev, inches per foot
    	*/
    	
    	shootMotorTop = new CANTalon(2);
    	shootMotorBottom = new CANTalon(3);
    	shootIntake = new CANTalon(6);
    	
    	rollerIntake = new CANTalon(9);
    	ballInSensor = new DigitalInput(0);
    	
    	defenseArmLeft = new CANTalon(8);
    	
    	defenseArmRight = new CANTalon(7);
    	defenseArmRight.changeControlMode(TalonControlMode.Follower);
    	defenseArmRight.set(defenseArmLeft.getDeviceID());
    	defenseArmRight.reverseOutput(true);
    	
    	intakeActuate = new Solenoid(6);
    	ringLight = new Solenoid(3);
    	
    	magnepotArm = new AnalogInput(1);
    	
    	pdp = new PowerDistributionPanel(17);
    	
    	gyro = new AnalogGyro(0);
		gyro.initGyro();
		
    	accelerometer = new BuiltInAccelerometer();
    			
    	
    	/*
    	 * Do later
    	upperArmLimit = new DigitalInput(2);
    	lowerArmLimit = new DigitalInput(3);
    	 */
    }
}
