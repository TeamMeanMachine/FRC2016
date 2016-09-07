package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team2471.frc.robot.Constants;
import org.team2471.frc.robot.commands.*;

public class AimGroup extends CommandGroup {

    public AimGroup(boolean finishOnTarget) { // TODO: Don't let any of this run if we don't have a ball
//    	addSequential( new BackUntilOuterWorks(0.3), 5.0 );
        addSequential(new StartShooter());
        addSequential(new RotateArmToAngle(-5.0));

        if (Constants.AIM_DROP_DISABLED) {
            addSequential(new DriveAim());
        } else {
            addSequential(new Aim2(finishOnTarget));
        }

        addSequential(new WaitCommand(1.0));
        addSequential(new RotateArmToAngle(62.0));
        addSequential(new StopShooter());
    }
}
