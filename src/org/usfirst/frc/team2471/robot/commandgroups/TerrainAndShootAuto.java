package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.LogCommand;
import org.usfirst.frc.team2471.robot.commands.QueueShot;
import org.usfirst.frc.team2471.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TerrainAndShootAuto extends CommandGroup {
    
    public  TerrainAndShootAuto() {    	
    	addSequential(new ResetGyroCommand());

    	addParallel(new RotateArmToAngle(0.0));
    	
    	addParallel(new QueueShot());

    	addSequential(new DriveDistanceCommand(9, 0, 0.9)); //Changed from 

    	addSequential(new DriveDistanceCommand(4, 0, 0.4));
    	addSequential(new WaitCommand(0.5));
    	
    	addSequential(new RotateArmToAngle(0.0));
    	
    	addSequential(new WaitCommand(1.0));
    	
    	addSequential(new BackUntilOuterWorks(0.4));
    	
    	addSequential(new LogCommand("AimAndShootGroup"));
    	addSequential(new AimAndShootGroup(true));

    	addSequential(new AutoAfterShoot());
    }
}
