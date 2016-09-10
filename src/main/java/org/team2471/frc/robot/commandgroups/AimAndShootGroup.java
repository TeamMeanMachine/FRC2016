package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.frc.robot.commands.*;

public class AimAndShootGroup extends CommandGroup {

    public AimAndShootGroup(boolean fastMode) {
        addSequential(new LogCommand("StartShooter"));
        addSequential(new StartShooter());

        addSequential(new LogCommand("RotateArmToAngle"));
        addSequential(new RotateArmToAngle(-5.0));

        addSequential(new LogCommand("Aim2"));
        addSequential(new Aim2(true, fastMode));

        addSequential(new LogCommand("Shoot"));
        addSequential(new Shoot());

        addSequential(new LogCommand("RotateArmToAngle"));
        addSequential(new RotateArmToAngle(62.0));
    }
}
