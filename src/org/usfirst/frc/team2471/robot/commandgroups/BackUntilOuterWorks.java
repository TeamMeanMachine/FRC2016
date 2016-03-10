package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveUntilFlat;
import org.usfirst.frc.team2471.robot.commands.DriveUntilTilted;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BackUntilOuterWorks extends CommandGroup {
    
    public  BackUntilOuterWorks() {
    	addSequential(new DriveUntilTilted(-0.5));
    	addSequential(new DriveUntilFlat(0.5));
    }
}
