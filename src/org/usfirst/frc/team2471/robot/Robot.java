
package org.usfirst.frc.team2471.robot;

import org.usfirst.frc.team2471.robot.commands.Aim;
import org.usfirst.frc.team2471.robot.commands.ExampleCommand;
import org.usfirst.frc.team2471.robot.subsystems.DefenseArm;
import org.usfirst.frc.team2471.robot.subsystems.Drive;
import org.usfirst.frc.team2471.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2471.robot.subsystems.Intake;
import org.usfirst.frc.team2471.robot.subsystems.Shooter;

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

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

	public static Shooter shooter;
	public static Intake intake;
	//public static Drive drive;
	public static Drive drive;
	public static DefenseArm defenseArm;
	
	public static boolean shoot;

	public static SendableChooser autoChooser;
    Command autonomousCommand;
    
    public static Preferences prefs;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
        drive = new Drive();
        intake = new Intake();
        shooter = new Shooter();
		defenseArm = new DefenseArm();
		
		prefs = Preferences.getInstance();
		
		SmartDashboard.putData("Shoot PID Config", shooter);
		
		SmartDashboard.putNumber("Top", prefs.getDouble("Top", 0.6));
		SmartDashboard.putNumber("Bottom", prefs.getDouble("Bottom", -0.4));
		SmartDashboard.putNumber("AimChange", prefs.getDouble("AimChange", 50.0));
		
		SmartDashboard.putData(shooter);
		
		oi = new OI();
		
        //Here is the Sendable for the autonomous command
        autoChooser = new SendableChooser();
        autoChooser.addObject("Nothing", new ExampleCommand());
        autoChooser.addDefault("Drive Straight", new ExampleCommand());
//        autoChooser.addObject("Name", new Command());
        SmartDashboard.putData("AutoChooser", autoChooser);

        SmartDashboard.putData(new Aim());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	prefs.putDouble("Top", SmartDashboard.getNumber("Top", 0.6));
    	prefs.putDouble("Bottom", SmartDashboard.getNumber("Bottom", -0.4));
    	prefs.putDouble("AimChange", SmartDashboard.getNumber("AimChange" , 40.0));
    	System.out.println("Saved prefs.");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}