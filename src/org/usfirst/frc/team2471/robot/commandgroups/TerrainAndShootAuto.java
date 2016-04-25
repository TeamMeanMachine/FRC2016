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
    	addSequential(new LogCommand("Resetting gyro"));
    	addSequential(new ResetGyroCommand());

    	addSequential(new LogCommand("Moving arm to 0 degrees"));
    	addParallel(new RotateArmToAngle(0.0));
    	
    	addSequential(new LogCommand("Queueing shot"));
    	addParallel(new QueueShot());

    	addSequential(new LogCommand("Driving forward"));
    	addSequential(new DriveDistanceCommand(9, 0, 0.9)); //Changed from 

    	addSequential(new DriveDistanceCommand(4, 0, 0.4));
    	addSequential(new WaitCommand(0.5));
    	
    	addSequential(new RotateArmToAngle(0.0));
    	
    	addSequential(new LogCommand("Waiting 1 second"));
    	addSequential(new WaitCommand(1.0));
    	
    	addSequential(new LogCommand("Backing to outer works"));
    	addSequential(new BackUntilOuterWorks(0.4));
    	
    	addSequential(new LogCommand("Shooting"));
    	addSequential(new AimAndShootGroup(true));

    	addSequential(new LogCommand("Rotating"));
    	addSequential(new RotateToAngle(0, 0.5, 2.0), 1);
    	
    	addSequential(new LogCommand("Driving backward"));
    	addSequential(new DriveDistanceCommand(9.0, 0.0, -0.8));
    }
}
