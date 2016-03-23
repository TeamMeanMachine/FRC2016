package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.DriveUntilButton;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SallyPortHelper extends CommandGroup {
	/**
	 * This command should be executed when the driver is within range of the sally port
	 */
	public SallyPortHelper() {
		double sallyPortAngle = SmartDashboard.getNumber("SallyPortPreset", 36.0);
		// Grab sally port door
		addSequential(new RotateArmToAngle(sallyPortAngle));
		// pull door back to safe position to flick
		addSequential(new DriveDistanceCommand(1.5, 0.0, -0.5));
		// Flick door left
		double currentGyro = RobotMap.gyro.getAngle();
		addSequential(new RotateToAngle(currentGyro - 20, 0.5));
		// flick back
		addSequential(new RotateToAngle(currentGyro, 0.5));
		// raise arm
		addParallel(new RotateArmToAngle(sallyPortAngle+10));
	}
}
