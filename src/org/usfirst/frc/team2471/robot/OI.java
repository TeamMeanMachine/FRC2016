package org.usfirst.frc.team2471.robot;

import org.usfirst.frc.team2471.robot.commandgroups.AimAndShootGroup;
import org.usfirst.frc.team2471.robot.commandgroups.BackUntilOuterWorks;
import org.usfirst.frc.team2471.robot.commandgroups.DrawBridgeHelper;
import org.usfirst.frc.team2471.robot.commandgroups.PickupBall;
import org.usfirst.frc.team2471.robot.commandgroups.ReleaseBall;
import org.usfirst.frc.team2471.robot.commandgroups.SallyPortHelper;
import org.usfirst.frc.team2471.robot.commands.CancelAuto;
import org.usfirst.frc.team2471.robot.commands.LightAction;
import org.usfirst.frc.team2471.robot.commands.SallyPortPreset;
import org.usfirst.frc.team2471.robot.commands.Shoot;
import org.usfirst.frc.team2471.robot.commands.SpitOutEmergency;
import org.usfirst.frc.team2471.robot.commands.StopBallIntake;

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
	public static JoystickButton sallyPortInit;
	public static JoystickButton drawbridgeHelper;
	public static JoystickButton driverLight;
	
	
	public OI(){
		driverStick = new Joystick(0);
		coStick = new Joystick(1);
		
		/*shootButton = new JoystickButton(coStick, 1);
		shootButton.whileHeld(new Shoot());*/
		
		aimButton = new JoystickButton(coStick, 3);
		aimButton.whenReleased(new AimAndShootGroup(false));
		
		fireButton = new JoystickButton(coStick, 6);  // want to put this on right trigger, but it is an axis on xbox controller
		fireButton.whenPressed(new Shoot());
		
		suckIn = new JoystickButton(driverStick, 6);
		suckIn.whenPressed(new PickupBall());
		suckIn.whenReleased(new StopBallIntake());
		
		sallyPort = new JoystickButton(coStick, 4);
		sallyPort.whenPressed(new SallyPortPreset());
		
		emergencySpit = new JoystickButton(driverStick, 8);
		emergencySpit.whileHeld(new ReleaseBall());
		
/*		backUntilTilted = new JoystickButton(driverStick, 7);
		backUntilTilted.whenPressed(new BackUntilOuterWorks());*/
		
		helperSequence = new JoystickButton(driverStick, 2);
		
		sallyPortInit = new JoystickButton(driverStick, 3);
		sallyPortInit.whenPressed(new SallyPortHelper());
		
		drawbridgeHelper = new JoystickButton(driverStick, 4);
		drawbridgeHelper.whenPressed(new DrawBridgeHelper());
		
		drawbridgeHelper = new JoystickButton(driverStick, 2);
		drawbridgeHelper.whenPressed(new CancelAuto());
		
		driverLight = new JoystickButton(driverStick, 7);
		driverLight.whenPressed(new LightAction());
	}
}

