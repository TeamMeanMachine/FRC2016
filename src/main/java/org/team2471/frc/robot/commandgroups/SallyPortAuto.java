package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team2471.frc.robot.commands.*;

/**
 *
 */
public class SallyPortAuto extends CommandGroup {

    public SallyPortAuto() {
        addSequential(new ResetGyroCommand());
        addParallel(new RotateArmToAngle(50.0));
        addParallel(new QueueShot());
        addSequential(new DriveDistanceCommand(3, 0, 0.7));
        addSequential(new DriveDistanceCommand(3, 0, 0.25), 1.0);

        addSequential(new SallyPortHelper());

        addSequential(new WaitCommand(0.25));
        addSequential(new RotateRelative(-10.0, 0.80, 3.0));
        addSequential(new DriveDistanceCommand(2, 0, 0.8));
        addSequential(new RotateRelative(5.0, 0.80, 3.0));
        addSequential(new DriveDistanceCommand(8, 0, 0.7));
        addSequential(new WaitCommand(0.5));

        //addSequential(new BackUntilOuterWorks(0.4), 1.5);
        addSequential(new AimAndShootGroup(true));


        //addSequential(new AutoAfterShoot());
        //addSequential(new DriveDistanceCommand(0.5, 0.0, -0.5));
        //addSequential(new RotateToAngle(0, 0.5, 2.0));
        //addSequential(new DriveDistanceCommand(8.0, 0.0, -0.8));
    }
}
