package org.usfirst.frc.team2471.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;
import org.usfirst.frc.team2471.robot.commands.TurnUntilBlobFound;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TerrainAndShotAuto extends CommandGroup {
    
    public  TerrainAndShotAuto() {

    	addSequential(new DriveDistanceCommand(12, 0, 0.7));
    	addSequential(new TurnUntilBlobFound(0.5), 2.0);
    	addSequential(new AimAndShootGroup());
    	addSequential(new RotateToAngle(0, 0.5));
    	addSequential(new DriveDistanceCommand(10, 0, -0.7));
    }
}
