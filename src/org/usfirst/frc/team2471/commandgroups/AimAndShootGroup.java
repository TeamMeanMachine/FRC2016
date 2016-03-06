package org.usfirst.frc.team2471.commandgroups;

import org.usfirst.frc.team2471.robot.commands.Aim2;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.StartShooter;
import org.usfirst.frc.team2471.robot.commands.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AimAndShootGroup extends CommandGroup {
    
    public  AimAndShootGroup(boolean finishOnTarget) {
        // Add Commands here:
        // e.g. adddSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would requires
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential( new StartShooter() );
    	addSequential( new RotateArmToAngle(22.5));
    	
    	addSequential( new Aim2(finishOnTarget));
    	
    	addSequential( new RotateArmToAngle(62.0));
    	addSequential( new StopShooter() );    	
    }
}
