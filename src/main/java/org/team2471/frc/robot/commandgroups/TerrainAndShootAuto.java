package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team2471.frc.robot.commands.DriveDistanceCommand;
import org.team2471.frc.robot.commands.QueueShot;
import org.team2471.frc.robot.commands.ResetGyroCommand;
import org.team2471.frc.robot.commands.RotateArmToAngle;

/**
 *
 */
public class TerrainAndShootAuto extends CommandGroup {

    public TerrainAndShootAuto() {
        addSequential(new ResetGyroCommand());
        addParallel(new RotateArmToAngle(0.0));
        addParallel(new QueueShot());
        addSequential(new DriveDistanceCommand(12, 0, 0.6));
        addSequential(new DriveDistanceCommand(4, 0, 0.4));
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveDistanceCommand(1.5, 0.0, -0.4));
        addSequential(new RotateArmToAngle(0.0));
        addSequential(new WaitCommand(0.5));

        addSequential(new BackUntilOuterWorks(.4));
        addSequential(new AimAndShootGroup(true));
    }
}
