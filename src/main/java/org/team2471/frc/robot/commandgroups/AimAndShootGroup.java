package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.frc.robot.commands.Aim2;
import org.team2471.frc.robot.commands.RotateArmToAngle;
import org.team2471.frc.robot.commands.Shoot;
import org.team2471.frc.robot.commands.StartShooter;

public class AimAndShootGroup extends CommandGroup {

    public AimAndShootGroup(boolean fastMode) {
        addSequential(new StartShooter());

        addSequential(new RotateArmToAngle(-5.0));

        addSequential(new Aim2(true, fastMode));

        addSequential(new Shoot());

        addSequential(new RotateArmToAngle(62.0));
    }
}
