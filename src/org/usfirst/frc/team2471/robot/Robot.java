
package org.usfirst.frc.team2471.robot;

import org.usfirst.frc.team2471.robot.commandgroups.ChevalAuto;
import org.usfirst.frc.team2471.robot.commandgroups.DrawBridgeAuto;
import org.usfirst.frc.team2471.robot.commandgroups.PortcullisAuto;
import org.usfirst.frc.team2471.robot.commandgroups.SallyPortAuto;
import org.usfirst.frc.team2471.robot.commandgroups.TerrainAndShootAuto;
import org.usfirst.frc.team2471.robot.commands.DoNothingAuto;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;
import org.usfirst.frc.team2471.robot.subsystems.DefenseArm;
import org.usfirst.frc.team2471.robot.subsystems.Drive;
import org.usfirst.frc.team2471.robot.subsystems.Intake;
import org.usfirst.frc.team2471.robot.subsystems.Shooter;
import org.usfirst.frc.team2471.util.Logger;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	/**
	 * This variable should be called to test if we should push print statements to the console that are for debugging purposes, so we can easily
	 * disable debug messages during competition so we can use the console for more important info.
	 */
	public static final boolean DEBUGMODE = true;

	public static OI oi;

	public static Shooter shooter;
	public static Intake intake;
	public static Drive drive;
	public static DefenseArm defenseArm;
	
	public static boolean shoot;
    public static boolean climbing;
    public static double ultrasonicLimit;

	public static SendableChooser autoChooser;
    Command autonomousCommand;
    
    public static Preferences prefs;
    
    public static Logger logger;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
	public void robotInit() {
    	RobotMap.init();
    	//Soon will change to DriveTalonPID
        drive = new Drive();
        intake = new Intake();
        shooter = new Shooter();
		defenseArm = new DefenseArm();
		
		SmartDashboard.putData(new RotateToAngle(0, 0.5, 2.0)); // Temporary
		
		prefs = Preferences.getInstance();
		logger = new Logger();
		climbing = false;
		
//		prefs.putDouble("AimChange", 15); // Temporary for testing without smart dashboard on 2/27/15
//		prefs.putDouble("Top", 3300);
//		prefs.putDouble("Bottom", 1920);
		SmartDashboard.putBoolean("LightON", false);
		SmartDashboard.putBoolean("ShooterEnable", false);
		SmartDashboard.putBoolean("AutoAim",true);
		
		// read prefs, put on dashboard
		SmartDashboard.putNumber("TopSetSpeed", prefs.getDouble("TopSetSpeed", 3300));
		SmartDashboard.putNumber("BottomSetSpeed", prefs.getDouble("BottomSetSpeed", 1920));
		SmartDashboard.putNumber("AimChange", prefs.getDouble("AimChange", 15.0));
		SmartDashboard.putNumber("ArmZeroVolts", prefs.getDouble("ArmZeroVolts", 2.290));
		SmartDashboard.putBoolean("UseGyro", prefs.getBoolean("UseGyro", false));
		SmartDashboard.putNumber("SallyPortPreset", prefs.getDouble("SallyPortPreset", 36.0));
		SmartDashboard.putNumber("DrawBridgePreset", prefs.getDouble("DrawBridgePreset", 65.0));
		SmartDashboard.putNumber("DefenseArmMax", prefs.getDouble("DefenseArmMax", 77.0));
		SmartDashboard.putNumber("DefenseArmMin", prefs.getDouble("DefenseArmMin", -13.0));
		SmartDashboard.putNumber("DefenseArmClimb", prefs.getDouble("DefenseArmClimb", 107.0));
		SmartDashboard.putBoolean("IntelVision", prefs.getBoolean("IntelVision", true));
		SmartDashboard.putNumber("UltrasonicLimit", prefs.getDouble("UltrasonicLimit", 0.1));
		SmartDashboard.putNumber("BallUltrasonicLimit", prefs.getDouble("BallUltrasonicLimit", 0.1));
		SmartDashboard.putNumber("ClimbForwardPower", prefs.getDouble("ClimbForwardPower", 0.15));
		SmartDashboard.putBoolean("ManualIntake", prefs.getBoolean("ManualIntake", false));
		SmartDashboard.putNumber("SuperMaxArmLimit", prefs.getDouble("SuperMaxArmLimit", 95));
		SmartDashboard.putNumber("AIM_DROP_TEST_ANGLE", 0);
		SmartDashboard.putBoolean("ShooterHasBall", shooter.hasBall());
		
		// Subsystems
		SmartDashboard.putData(defenseArm);
		SmartDashboard.putData(drive);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(shooter);

		SmartDashboard.putNumber("GYRO_TARGET", 0);
		
		SmartDashboard.putBoolean("PING", false);
		
		SmartDashboard.putBoolean("RumbleHasBlob", false);
		SmartDashboard.putBoolean("RumbleAimOnTarget", false);
		SmartDashboard.putBoolean("RumbleHasPressure", false);
		SmartDashboard.putBoolean("RumbleTopError", false);
		SmartDashboard.putBoolean("RumbleBottomErrror", false);
		SmartDashboard.putBoolean("Rumble", false);
		
		oi = new OI();
		
        //Here is the Sendable for the autonomous command
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Terrain and Shoot", new TerrainAndShootAuto());
        autoChooser.addObject("Don't Move", new DoNothingAuto());
        autoChooser.addObject("Sally Port", new SallyPortAuto());
        autoChooser.addObject("Draw Bridge", new DrawBridgeAuto());
        autoChooser.addObject("Chival de Frise", new ChevalAuto());
        autoChooser.addObject("Portcullis", new PortcullisAuto());
        SmartDashboard.putData("AutoChooser", autoChooser);
        
        drive.resetEncoders();
    }
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Defense Arm Voltage", RobotMap.magnepotArm.getVoltage());
		SmartDashboard.putNumber("Defense Arm Position", Robot.defenseArm.voltageToAngle(RobotMap.magnepotArm.getVoltage()));
		SmartDashboard.putNumber("Pressure", RobotMap.pressureSensor.getPressure());

		SmartDashboard.putNumber("UltrasonicR", RobotMap.backupUltrasonicRight.getRangeInches());
		SmartDashboard.putNumber("UltrasonicL", RobotMap.backupUltrasonicLeft.getRangeInches());
        SmartDashboard.putNumber("GYRO_ANGLE", RobotMap.gyro.getAngle());
	}

    @Override
	public void autonomousInit() {
    	drive.resetEncoders();
    	RobotMap.gyro.reset();
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
	public void autonomousPeriodic() {
        Scheduler.getInstance().run();

        SmartDashboard.putNumber("GYRO_ANGLE", RobotMap.gyro.getAngle());
    }

    @Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        defenseArm.setTargetAngle(defenseArm.getPosition());
        drive.resetEncoders();
		Robot.climbing = false;
		RobotMap.gyro.reset();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
	public void disabledInit(){
    	// write smartboard stuff to prefs
    	prefs.putDouble("TopSetSpeed", SmartDashboard.getNumber("TopSetSpeed"));
    	prefs.putDouble("BottomSetSpeed", SmartDashboard.getNumber("BottomSetSpeed"));
    	prefs.putDouble("AimChange", SmartDashboard.getNumber("AimChange"));
    	prefs.putDouble("ArmZeroVolts", SmartDashboard.getNumber("ArmZeroVolts"));
    	prefs.putBoolean("UseGyro", SmartDashboard.getBoolean("UseGyro"));
    	prefs.putDouble("SallyPortPreset", SmartDashboard.getNumber("SallyPortPreset"));
    	prefs.putDouble("DrawBridgePreset", SmartDashboard.getNumber("DrawBridgePreset"));
    	prefs.putDouble("DefenseArmMax", SmartDashboard.getNumber("DefenseArmMax"));
    	prefs.putDouble("DefenseArmMin", SmartDashboard.getNumber("DefenseArmMin"));
    	prefs.putDouble("DefenseArmClimb", SmartDashboard.getNumber("DefenseArmClimb"));
    	prefs.putBoolean("IntelVision", SmartDashboard.getBoolean("IntelVision"));
    	prefs.putDouble("UltrasonicLimit", SmartDashboard.getNumber("UltrasonicLimit"));
    	prefs.putDouble("BallUltrasonicLimit", SmartDashboard.getNumber("BallUltrasonicLimit"));
    	prefs.putDouble("ClimbForwardPower", SmartDashboard.getNumber("ClimbForwardPower"));
    	prefs.putBoolean("ManualIntake", SmartDashboard.getBoolean("ManualIntake"));
    	prefs.putDouble("SuperMaxArmLimit", SmartDashboard.getNumber("SuperMaxArmLimit"));
    	System.out.println("Saved prefs.");
    	
		logger.update();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
	public void teleopPeriodic() {
        Scheduler.getInstance().run();
        if(SmartDashboard.getBoolean("LightON")) {
        	shooter.setLights(true);
        }
        SmartDashboard.putNumber("GYRO_ANGLE", RobotMap.gyro.getAngle());
        SmartDashboard.putNumber("gyroRate", RobotMap.gyro.getRate());
        
        SmartDashboard.putNumber("CompressorCurrent", RobotMap.compressor.getCompressorCurrent());
		SmartDashboard.putNumber("Pressure", RobotMap.pressureSensor.getPressure());
		
		SmartDashboard.putNumber("UltrasonicR", RobotMap.backupUltrasonicRight.getRangeInches());
		SmartDashboard.putNumber("UltrasonicL", RobotMap.backupUltrasonicLeft.getRangeInches());
		SmartDashboard.putBoolean("ShooterHasBall", shooter.hasBall());
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
	public void testPeriodic() {
        LiveWindow.run();
    }
}