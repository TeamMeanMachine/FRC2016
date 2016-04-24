package org.usfirst.frc.team2471.robot;

import org.usfirst.frc.team2471.robot.commandgroups.AimGroup;
import org.usfirst.frc.team2471.robot.commandgroups.BackUntilOuterWorks;
import org.usfirst.frc.team2471.robot.commandgroups.ChevalHelper;
import org.usfirst.frc.team2471.robot.commandgroups.DrawBridgeHelper;
import org.usfirst.frc.team2471.robot.commandgroups.PickupBallManual;
import org.usfirst.frc.team2471.robot.commandgroups.PortcullisHelper;
import org.usfirst.frc.team2471.robot.commandgroups.ReleaseBall;
import org.usfirst.frc.team2471.robot.commandgroups.SallyPortHelper;
import org.usfirst.frc.team2471.robot.commands.AimDropTest;
import org.usfirst.frc.team2471.robot.commands.CancelAuto;
import org.usfirst.frc.team2471.robot.commands.LightAction;
import org.usfirst.frc.team2471.robot.commands.PickUpBallAuto;
import org.usfirst.frc.team2471.robot.commands.PingSmartDashboard;
import org.usfirst.frc.team2471.robot.commands.SallyPortPreset;
import org.usfirst.frc.team2471.robot.commands.Shoot;
import org.usfirst.frc.team2471.robot.commands.StopBallIntake;
import org.usfirst.frc.team2471.util.JoystickPOVButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public static Joystick driverStick, coStick;
	public static JoystickButton shootButton;
	public static JoystickButton aimButton;
	public static JoystickButton armRotateUpButton;
	public static JoystickButton armRotateDownButton;
	public static JoystickButton fireButton;
	public static JoystickButton suckIn;
	public static JoystickButton sallyPort;
	public static JoystickButton emergencySpit;
	public static JoystickButton backUntilTilted;
	public static JoystickButton helperSequence;
	public static JoystickButton driverLight;
	public static JoystickButton manualSuck;
	public static JoystickButton backupUltrasonic;
	public static JoystickButton cancelAuto;
	public static JoystickButton aimDropTest;
	
	public static JoystickButton pingButton;

	public static JoystickPOVButton sallyPortHelper;
	public static JoystickPOVButton drawbridgeHelper;
	public static JoystickPOVButton chevalHelper;
	public static JoystickPOVButton portcullisHelper;
	
	public OI(){
		driverStick = new Joystick(0);
		coStick = new Joystick(1);
		
		/*shootButton = new JoystickButton(coStick, 1);
		shootButton.whileHeld(new Shoot());*/
		
		aimButton = new JoystickButton(coStick, 3);
		aimButton.whenReleased(new AimGroup(false));
		
		fireButton = new JoystickButton(coStick, 6);  // want to put this on right trigger, but it is an axis on xbox controller
		fireButton.whenPressed(new Shoot());
		
		suckIn = new JoystickButton(driverStick, 6);
		suckIn.whenPressed(new PickUpBallAuto());
		suckIn.whenReleased(new StopBallIntake());
		
		sallyPort = new JoystickButton(coStick, 4);
		sallyPort.whenPressed(new SallyPortPreset());
		
		emergencySpit = new JoystickButton(driverStick, 8);
		emergencySpit.whenPressed(new ReleaseBall());
		
/*		backUntilTilted = new JoystickButton(driverStick, 7);
		backUntilTilted.whenPressed(new BackUntilOuterWorks());*/
		
		helperSequence = new JoystickButton(driverStick, 2);
		


		cancelAuto = new JoystickButton(driverStick, 9);
		cancelAuto.whenPressed(new CancelAuto());
		
		driverLight = new JoystickButton(driverStick, 4);
		driverLight.whenPressed(new LightAction());
		
		manualSuck = new JoystickButton(driverStick, 1);
		manualSuck.whenPressed(new PickupBallManual());
		
		backupUltrasonic = new JoystickButton(driverStick, 7);
		backupUltrasonic.whenPressed(new BackUntilOuterWorks(0.4));
		
		aimDropTest = new JoystickButton(coStick, 1);
		aimDropTest.whenPressed(new AimDropTest());
		
		sallyPortHelper = new JoystickPOVButton(driverStick, 0);
		sallyPortHelper.whenPressed(new SallyPortHelper());

		drawbridgeHelper = new JoystickPOVButton(driverStick, 90);
		drawbridgeHelper.whenPressed(new DrawBridgeHelper());		
		
		chevalHelper = new JoystickPOVButton(driverStick, 180);
		chevalHelper.whenPressed(new ChevalHelper());
		
		portcullisHelper = new JoystickPOVButton(driverStick, 270);
		portcullisHelper.whenPressed(new PortcullisHelper());
		
		pingButton = new JoystickButton(coStick, 7);
		pingButton.whenPressed(new PingSmartDashboard());
	}
}

