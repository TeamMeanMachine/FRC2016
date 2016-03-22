package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.ColorSensor;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUntilColor extends Command {
	private int red, green, blue;
	private double power;

    public DriveUntilColor(int _red, int _green, int _blue, double _power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	red = _red;
    	green = _green;
    	blue = _blue;
    	power = _power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.colorSensor.ledOn();
    	if (!isFinished())
            Robot.drive.setSpeed(0, power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	ColorSensor.ColorData color = RobotMap.colorSensor.getRawData();
    	int redDif = Math.abs(color.r - red);
    	int greenDif = Math.abs(color.g - green);
    	int blueDif = Math.abs(color.b - blue);
        return redDif < 2 && greenDif < 2 && blueDif < 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.colorSensor.ledOff();
    	Robot.drive.setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
