package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.frc.robot.commands.DriveDistanceCommand;
import org.team2471.frc.robot.commands.RotateToAngle;

public class AutoAfterShoot extends CommandGroup {
    public AutoAfterShoot() {
        addSequential(new RotateToAngle(0, 0.5, 2.0), 1);
        addSequential(new DriveDistanceCommand(9, 0, -0.8));
    }

}
