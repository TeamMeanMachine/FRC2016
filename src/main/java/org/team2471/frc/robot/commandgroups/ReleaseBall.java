package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.frc.robot.commands.SpitBall;
import org.team2471.frc.robot.commands.UnloadShooter;

public class ReleaseBall extends CommandGroup {

    public ReleaseBall() {
        addSequential(new UnloadShooter(), 3);
        addSequential(new SpitBall());
    }
}
