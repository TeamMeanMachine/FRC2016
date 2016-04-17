package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.CenterBall;
import org.usfirst.frc.team2471.robot.commands.QueueShot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupBallManual extends CommandGroup {
    
    public  PickupBallManual() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new CenterBall());
    	addSequential(new MicroBallHelper());
    	addSequential(new MicroBallHelper());
    	addSequential(new QueueShot());
    }
}
