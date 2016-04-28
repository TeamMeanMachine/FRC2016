package org.usfirst.frc.team2471.robot;

import org.usfirst.frc.team2471.util.TMMGyro;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static PowerDistributionPanel pdp;
	
/*_____________________________________Drive-------------------------------- */
    private static CANTalon leftSlave, leftMaster;
    public static CANTalon leftDrive;
    
//    public static AnalogInput backupUltrasonic;
    public static Ultrasonic backupUltrasonicRight;
    public static Ultrasonic backupUltrasonicLeft;
    
    
    private static CANTalon rightSlave, rightMaster;
    public static CANTalon rightDrive;

    public static CANTalon liftExtension;
    
    public static Solenoid aimDropCylinder;
    public static Solenoid pto;
    public static Solenoid ratchet;
    public static Solenoid flashlight;

    
/*_____________________________Shooter____________________________________ */
    public static CANTalon shootMotorTop, shootMotorBottom, shootIntake;
    public static DigitalInput shooterBallSensor;
    
/*_________________________Aimer---------------------------------------*/
    public static CANTalon aimer;
    public static Encoder aimEncoder;
    
    
    
/*_____________________________Intake____________________________________ */
    
    public static CANTalon rollerIntake;
    public static DigitalInput intakeBallSensor;
    public static Ultrasonic ballUltrasonic;
    
/*______________________________Defense Arm_________________________________*/
    private static CANTalon defenseArmLeft, defenseArmRight;
    public static CANTalon defenseArm;
    
    public static Solenoid intakeActuate;
    public static Solenoid ringLight;
    
    
    public static AnalogInput magnepotArm;
    
    public static AnalogGyro gyro; 
    //public static TMMGyro gyro;
    
    public static BuiltInAccelerometer accelerometer;
    
    public static Compressor compressor;
    
    public static double accelDownX, accelDownY, accelDownZ;
    
    public static PressureSensor pressureSensor;
    
    public static void init(){
//    	backupUltrasonic = new AnalogInput(4);
    	backupUltrasonicRight = new Ultrasonic(3, 2);
    	backupUltrasonicLeft = new Ultrasonic(5, 4);
    	backupUltrasonicRight.setAutomaticMode(true);
    	backupUltrasonicLeft.setAutomaticMode(true);
    	
    	
    	
    	aimer = new CANTalon(13);
    	
    	leftMaster = new CANTalon(14);
    	leftMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	leftMaster.configEncoderCodesPerRev(845);
    	leftDrive = leftMaster;
    	
    	leftSlave = new CANTalon(15);
    	leftSlave.changeControlMode(TalonControlMode.Follower);
    	leftSlave.set(leftMaster.getDeviceID());
    	//Encoder 2.1 times wheel
    	//24.178 in per wheel rev
    	//0.49 rev per ft
    	
    	rightMaster = new CANTalon(1);
    	rightMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	rightMaster.configEncoderCodesPerRev(845);
    	rightDrive = rightMaster;
    	
    	rightSlave = new CANTalon(0);
    	rightSlave.changeControlMode(TalonControlMode.Follower);
    	rightSlave.set(rightMaster.getDeviceID());
    	
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
    	shooterBallSensor = new DigitalInput(1);
    	
    	rollerIntake = new CANTalon(9);
    	intakeBallSensor = new DigitalInput(0);
    	
    	defenseArmLeft = new CANTalon(8);
    	defenseArm = defenseArmLeft;
    	
    	defenseArmRight = new CANTalon(7);
    	defenseArmRight.changeControlMode(TalonControlMode.Follower);
    	defenseArmRight.set(defenseArmLeft.getDeviceID());
    	defenseArmRight.reverseOutput(true);
    	
    	intakeActuate = new Solenoid(6);
    	ringLight = new Solenoid(3);
    	
    	magnepotArm = new AnalogInput(1);
    	
    	pdp = new PowerDistributionPanel(0);
    	
    	/*analogGyro = new AnalogGyro(0);
		analogGyro.initGyro();
		
        try {
             Communicate w/navX MXP via the MXP SPI Bus.                                     
             Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     
             See http://navx-mxp..com/guidance/selecting-an-interface/ for details. 
            gyro = new TMMGyro(SPI.Port.kOnboardCS0);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }*/
    	
    	gyro = new AnalogGyro(0);
    	gyro.initGyro();
		
    	accelerometer = new BuiltInAccelerometer();
        accelDownX = accelerometer.getX();  // robot needs to be flat when this runs
        accelDownY = accelerometer.getY();
        accelDownZ = accelerometer.getZ();

        // normalize
        double length = Math.sqrt(accelDownX*accelDownX + accelDownY*accelDownY + accelDownZ*accelDownZ);
        if (length>0.0) {
	        accelDownY /= length;
	        accelDownZ /= length;
        }
        
        compressor = new Compressor();
        
        ratchet = new Solenoid(7);
        ratchet.set(false);
        
        pressureSensor = new PressureSensor(2);
        
//        colorSensor = new ColorSensor(2, ColorSensor.IntegrationTime.TCS34725_INTEGRATIONTIME_2_4MS, ColorSensor.SensorGain.TCS34725_GAIN_16X);
        
//        vision = new VisionProcessor();
//        vision.start();
//        vision.suspend();
        flashlight = new Solenoid(2);
    }
}
