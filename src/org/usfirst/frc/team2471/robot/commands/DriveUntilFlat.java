package org.usfirst.frc.team2471.robot.commands;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilFlat extends Command {

	double power;
	
    public DriveUntilFlat(double _power) {
        requires(Robot.drive);
        
        power = _power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (!isFinished())
            Robot.drive.setSpeed(0, power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        double accelX = RobotMap.accelerometer.getX();
        double accelY = RobotMap.accelerometer.getY();
        double accelZ = RobotMap.accelerometer.getZ();

        // normalize
        double length = Math.sqrt(accelX*accelX + accelY*accelY + accelZ*accelZ);
        accelX /= length;
        accelY /= length;
        accelZ /= length;
        
    	double dotproduct = RobotMap.accelDownX * accelX + RobotMap.accelDownY * accelY + RobotMap.accelDownZ * accelZ;
    	
    	double angle = Math.acos(dotproduct);
    	
    	return angle < 5.0;
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
