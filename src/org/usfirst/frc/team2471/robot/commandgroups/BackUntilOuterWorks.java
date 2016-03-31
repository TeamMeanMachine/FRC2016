package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveUntilUltrasonic;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BackUntilOuterWorks extends CommandGroup {
    
    public  BackUntilOuterWorks() {
//    	addSequential(new DriveUntilColor(64, 60, 40, -0.5));
//    	addSequential(new DriveUntilColor(57, 56, 38 ,0.5));
    	addSequential(new DriveUntilUltrasonic(0.3));
    }
}
