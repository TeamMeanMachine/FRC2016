package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.DriveUntilUltrasonic;
import org.usfirst.frc.team2471.robot.commands.QueueShot;
import org.usfirst.frc.team2471.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngleWait;
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
    	addSequential(new DriveDistanceCommand(12, 0, .9));
    	addSequential(new DriveDistanceCommand(4, 0, 0.4));
    	//addSequential(new RotateArmToAngleWait(0.0, 3));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new DriveDistanceCommand(1.5,  0.0, -0.4));
    	addSequential(new RotateArmToAngle(0.0));
    	//addSequential(new AimAndShootGroup(false));
    	addSequential(new WaitCommand(0.5));
    	//addSequential(new AutoAfterShoot());
    	
    	addSequential(new BackUntilOuterWorks(.4));
    	addSequential(new AimAndShootGroup(true));
    	
    	addSequential(new DriveDistanceCommand(0.5, 0.0, -0.5));
    	addSequential(new RotateToAngle(0, 0.5, 2.0));
    	addSequential(new DriveDistanceCommand(8.0, 0.0, -0.8));
    }
}
