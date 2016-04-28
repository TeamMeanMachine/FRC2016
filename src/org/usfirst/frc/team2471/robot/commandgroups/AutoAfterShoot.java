package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoAfterShoot extends CommandGroup {
	public AutoAfterShoot() {
		addSequential(new RotateToAngle(0, 0.5, 2.0), 1);	
		addSequential(new DriveDistanceCommand(9, 0, -0.8));
	}

}
