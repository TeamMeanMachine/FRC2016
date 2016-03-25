package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateRelative;

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
		addSequential(new DriveDistanceCommand(3.5, 0.0, -0.4));
		// Flick door left
		addSequential(new RotateRelative(-45.0, 0.80, 3.0));
		// raise arm
		addParallel(new RotateArmToAngle(sallyPortAngle + 10));
		// flick back
		addSequential(new RotateRelative(30.0, 0.80, 3.0));
		// drive forward
		addSequential(new DriveDistanceCommand(1.0, 0.0, 0.4));
	}
}
