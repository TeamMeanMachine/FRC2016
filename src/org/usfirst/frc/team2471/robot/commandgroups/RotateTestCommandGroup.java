package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RotateTestCommandGroup extends CommandGroup {
	public RotateTestCommandGroup() {
		addSequential(new RotateToAngle(0, 0.5, 2));
		addSequential(new WaitCommand(1));
		
		addSequential(new RotateToAngle(90, 0.5, 2));
		addSequential(new WaitCommand(1));
		
		addSequential(new RotateToAngle(180, 0.5, 2));
		addSequential(new WaitCommand(1));
		
		addSequential(new RotateToAngle(360, 0.5, 2));
		addSequential(new WaitCommand(1));
		
		addSequential(new RotateToAngle(180, 0.5, 2));
		addSequential(new WaitCommand(1));

		addSequential(new RotateToAngle(360, 0.5, 2));
		addSequential(new WaitCommand(1));
	}
}
