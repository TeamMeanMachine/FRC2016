package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.SpitBall;
import org.usfirst.frc.team2471.robot.commands.UnloadShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReleaseBall extends CommandGroup {
	
	public ReleaseBall() {
		addSequential(new UnloadShooter(), 0.7);
		addSequential(new SpitBall());
	}
}
