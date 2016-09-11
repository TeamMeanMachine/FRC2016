package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team2471.frc.robot.commands.IntakeDown;
import org.team2471.frc.robot.commands.QueueShot;

// For demos only
public class DoubleBallIntake extends CommandGroup {
    public DoubleBallIntake() {
        addSequential(new IntakeDown(5));
        addSequential(new WaitCommand(0.7));
        addSequential(new QueueShot());
        addSequential(new IntakeDown(5));
    }
}
