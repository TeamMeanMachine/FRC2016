package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.Aim2;
import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.QueueShot;
import org.usfirst.frc.team2471.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;
import org.usfirst.frc.team2471.robot.commands.Shoot;
import org.usfirst.frc.team2471.robot.commands.StartShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DrawBridgeAuto extends CommandGroup {
    
    public  DrawBridgeAuto() {
    	addSequential(new ResetGyroCommand());
    	addParallel(new RotateArmToAngle(75.0));
    	addParallel(new QueueShot());
    	addSequential(new DriveDistanceCommand(2, 0, 0.5));
    	addSequential(new DriveDistanceCommand(1, 0, 0.4),1.0);

    	addSequential(new DrawBridgeHelper());

    	addSequential(new WaitCommand(0.5));
    	addSequential(new DriveDistanceCommand(2, 0, 0.4));
    	addSequential(new RotateToAngle( 0.0, 0.5, 2.0 ));
    	addSequential(new DriveDistanceCommand(6, 0, 0.5));

    	addSequential(new WaitCommand(0.5));
    	//addSequential(new RingLightCommand(true));
    	//addSequential(new TurnUntilBlobFound(0.5), 2.0);
    	addSequential(new StartShooter());
    	addSequential(new Aim2(true));
    	addSequential(new Shoot());
    	
    	addSequential(new RotateToAngle(0, 0.5, 2.0));
    	// addSequential(new DriveDistanceCommand(10, 0, -0.7));
    }
}
