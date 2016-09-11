package org.team2471.frc.robot;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team2471.frc.robot.commandgroups.*;
import org.team2471.frc.robot.commands.LightAction;
import org.team2471.frc.robot.commands.Shoot;
import org.team2471.frc.robot.commands.StopCommand;
import org.team2471.frc.util.JoystickPOVButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static Joystick driverStick, coStick;
    public static JoystickButton shootButton;
    public static JoystickButton aimButton;
    public static JoystickButton armRotateUpButton;
    public static JoystickButton armRotateDownButton;
    public static JoystickButton fireButton;
    public static JoystickButton suckIn;
    public static JoystickButton fastShot;
    public static JoystickButton emergencySpit;
    public static JoystickButton backUntilTilted;
    public static JoystickButton driverLight;
    public static JoystickButton manualSuck;
    public static JoystickButton backupUltrasonic;
    public static JoystickButton cancelAuto;
    public static JoystickButton aimDropTest;
    public static JoystickButton testButton;

    public static JoystickButton pingButton;

    public static JoystickPOVButton sallyPortHelper;
    public static JoystickPOVButton drawbridgeHelper;
    public static JoystickPOVButton chevalHelper;
    public static JoystickPOVButton portcullisHelper;

    public OI() {
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

        if(!Constants.DEPEND_ON_SHOOTER_SENSOR) {
            JoystickButton doubleIntakeButton = new JoystickButton(driverStick, 5);
            doubleIntakeButton.whenPressed(new DoubleBallIntake());
        }

        //fastShot = new JoystickButton(coStick, 4);
        //fastShot.whenPressed(new AimAndShootGroup(true));

        emergencySpit = new JoystickButton(driverStick, 8);
        emergencySpit.whenPressed(new ReleaseBall());
		
/*		backUntilTilted = new JoystickButton(driverStick, 7);
		backUntilTilted.whenPressed(new BackUntilOuterWorks());*/

        cancelAuto = new JoystickButton(driverStick, 2);
        cancelAuto.whenPressed(new StopCommand());

        driverLight = new JoystickButton(driverStick, 4);
        driverLight.whenPressed(new LightAction());

        backupUltrasonic = new JoystickButton(driverStick, 7);
        backupUltrasonic.whenPressed(new BackUntilOuterWorks(0.3));

//		aimDropTest = new JoystickButton(coStick, 1);
//		aimDropTest.whenPressed(new AimDropTest());

        sallyPortHelper = new JoystickPOVButton(driverStick, 0);
        sallyPortHelper.whenPressed(new SallyPortHelper());

        drawbridgeHelper = new JoystickPOVButton(driverStick, 90);
        drawbridgeHelper.whenPressed(new DrawBridgeHelper());

        chevalHelper = new JoystickPOVButton(driverStick, 180);
        chevalHelper.whenPressed(new ChevalHelper());

        portcullisHelper = new JoystickPOVButton(driverStick, 270);
        portcullisHelper.whenPressed(new PortcullisHelper());

    }
}

