package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.Aim2;
import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.QueueShot;
import org.usfirst.frc.team2471.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team2471.robot.commands.RingLightCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;
import org.usfirst.frc.team2471.robot.commands.Shoot;
import org.usfirst.frc.team2471.robot.commands.StartShooter;
import org.usfirst.frc.team2471.robot.commands.TurnUntilBlobFound;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class SallyPortAuto extends CommandGroup {
    
    public  SallyPortAuto() {
    	addSequential(new ResetGyroCommand());
    	addParallel(new RotateArmToAngle(50.0));
    	addParallel(new QueueShot());
    	addSequential(new DriveDistanceCommand(4, 0, 0.7));
    	addSequential(new DriveDistanceCommand(1, 0, 0.4));

    	addSequential(new SallyPortHelper());

    	addSequential(new WaitCommand(0.5));
    	addSequential(new RotateToAngle( -5.0, 0.5, 2.0 ));
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
