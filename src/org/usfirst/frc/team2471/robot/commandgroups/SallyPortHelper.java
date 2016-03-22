package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.commands.DriveUntilButton;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngleWait;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SallyPortHelper extends CommandGroup {
	/**
	 * This command should be executed when the driver is within range of the sally port
	 */
	public SallyPortHelper() {
		double sallyPortAngle = SmartDashboard.getNumber("SallyPortPreset", 36.0);
		// Grab sally port door
		addSequential(new RotateArmToAngleWait(sallyPortAngle, 1));
		// Have driver pull door back to safe position to flick
		addSequential(new DriveUntilButton(OI.helperSequence));
		// Flick door
	}
}
