package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team2471.frc.robot.commands.RotateToAngle;

public class RotateTestCommandGroup extends CommandGroup {
    public RotateTestCommandGroup() {
        addSequential(new RotateToAngle(0, 0.5, 2));
        addSequential(new WaitCommand(1));

        addSequential(new RotateToAngle(90, 0.5, 2));
        addSequential(new WaitCommand(1));

        addSequential(new RotateToAngle(180, 0.5, 2));
        addSequential(new WaitCommand(1));

        addSequential(new RotateToAngle(360, 0.5, 2));
        addSequential(new WaitCommand(1));

        addSequential(new RotateToAngle(180, 0.5, 2));
        addSequential(new WaitCommand(1));

        addSequential(new RotateToAngle(360, 0.5, 2));
        addSequential(new WaitCommand(1));
    }
}
