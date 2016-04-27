package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveWithHeading;
import org.usfirst.frc.team2471.robot.commands.QueueShot;
import org.usfirst.frc.team2471.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PortcullisAuto extends CommandGroup {
	public PortcullisAuto() {
		addSequential(new ResetGyroCommand());
		addParallel(new RotateArmToAngle(50.0));
		addParallel(new QueueShot());
		addSequential(new DriveWithHeading(2, 0.7));
		addSequential(new DriveWithHeading(1.5, 0.3));
		addSequential(new WaitCommand(0.5));
		
		addSequential(new PortcullisHelper());
	
		addSequential(new RotateToAngle( 0.0, 0.5, 2.0 ));
		addSequential(new WaitCommand(1));
		
		addSequential(new BackUntilOuterWorks(0.4), 1.5);
		addSequential(new AimAndShootGroup(false));
	}

}
