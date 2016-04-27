package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.DriveWithHeading;
import org.usfirst.frc.team2471.robot.commands.QueueShot;
import org.usfirst.frc.team2471.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SallyPortAuto extends CommandGroup {
    
    public  SallyPortAuto() {
    	addSequential(new ResetGyroCommand());
    	addParallel(new RotateArmToAngle(50.0));
    	addParallel(new QueueShot());
    	addSequential(new DriveWithHeading(3, 0.7));
    	addSequential(new DriveWithHeading(3, 0.25), 1.0);

    	addSequential(new SallyPortHelper());

    	//addSequential(new WaitCommand(0.5));
    	addSequential(new RotateToAngle( -5.0, 0.5, 2.0 ));
    	addSequential(new DriveDistanceCommand(2, 0, 0.8));
    	addSequential(new RotateToAngle( 0.0, 0.5, 2.0 ));
    	addSequential(new DriveDistanceCommand(8, 0, 0.7));
    	//addSequential(new WaitCommand(0.5));
    	
    	addSequential(new BackUntilOuterWorks(0.4), 1.5);
    	addSequential(new AimAndShootGroup(false));
    	

    	addSequential(new AutoAfterShoot());
    	addSequential(new DriveDistanceCommand(0.5, 0.0, -0.5));
    	addSequential(new RotateToAngle(0, 0.5, 2.0));
    	addSequential(new DriveDistanceCommand(8.0, 0.0, -0.8));
    }
}
