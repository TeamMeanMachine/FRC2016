package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveWithHeading;
import org.usfirst.frc.team2471.robot.commands.QueueShot;
import org.usfirst.frc.team2471.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngleWait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TerrainAndShootAuto extends CommandGroup {
    
    public  TerrainAndShootAuto() {    	
    	addSequential(new ResetGyroCommand());

    	addParallel(new RotateArmToAngle(0.0));
    	
    	addParallel(new QueueShot());

    	addSequential(new DriveWithHeading(9, 1));

    	addSequential(new DriveWithHeading(4, 0.4));
    	
    	addSequential(new RotateArmToAngleWait(0.0, 3));
    	
    	addSequential(new BackUntilOuterWorks(0.4));
    	
    	addSequential(new AimAndShootGroup(true));

    	addSequential(new AutoAfterShoot());
    }
}
