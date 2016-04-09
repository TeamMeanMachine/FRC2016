package org.usfirst.frc.team2471.robot.commands;
import org.usfirst.frc.team2471.robot.ColorSensor;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveUntilUltrasonic extends Command {

	double power;
	
    public DriveUntilUltrasonic(double _power) {
        requires(Robot.drive);
        
        power = _power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!isFinished())
    		Robot.drive.setSpeed(0, -power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double ultrasonicLimit = SmartDashboard.getNumber("UltrasonicLimit",0.1);
    	return RobotMap.backupUltrasonic.getVoltage() <= ultrasonicLimit || isTimedOut() || (Robot.oi.driverStick.getRawAxis(1) < -.3);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
