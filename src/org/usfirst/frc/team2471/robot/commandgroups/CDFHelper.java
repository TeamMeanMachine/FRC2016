package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CDFHelper extends CommandGroup {
    
    public  CDFHelper() {
        addSequential(new RotateArmToAngle(-10.0));
        addSequential(new DriveDistanceCommand(0.5, 0.0, 0.5));
        addSequential(new DriveDistanceCommand(4.0, 0.0, 0.5));
        addParallel(new RotateArmToAngle(10.0));
    }
}
