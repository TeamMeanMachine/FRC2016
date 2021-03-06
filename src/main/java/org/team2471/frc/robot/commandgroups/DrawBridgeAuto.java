package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team2471.frc.robot.commands.*;

/**
 *
 */
public class DrawBridgeAuto extends CommandGroup {

    public DrawBridgeAuto() {
        addSequential(new ResetGyroCommand());
        addParallel(new RotateArmToAngle(75.0));
        addParallel(new QueueShot());
        addSequential(new DriveDistanceCommand(2, 0, 0.5));
        addSequential(new DriveDistanceCommand(1, 0, 0.4), 1.0);

        addSequential(new DrawBridgeHelper());

        addSequential(new WaitCommand(0.5));
        addSequential(new DriveDistanceCommand(2, 0, 0.4));
        addSequential(new RotateToAngle(0.0, 0.5, 2.0));
        addSequential(new DriveDistanceCommand(6, 0, 0.5));

        addSequential(new WaitCommand(0.5));

        addSequential(new AimAndShootGroup(false));

        addSequential(new RotateToAngle(0, 0.5, 2.0));
        // addSequential(new DriveDistanceCommand(10, 0, -0.7));

        addSequential(new AutoAfterShoot());
    }
}
