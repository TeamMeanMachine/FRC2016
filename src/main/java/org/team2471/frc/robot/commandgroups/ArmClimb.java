package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.commands.ArmNoLimit;

/**
 *
 */
public class ArmClimb extends CommandGroup {

    public ArmClimb() {
        addSequential(new ArmNoLimit(SmartDashboard.getNumber("DefenseArmClimb", 107.0)));
        addSequential(new WaitCommand(.5));
        addSequential(new ArmNoLimit(SmartDashboard.getNumber("SuperMaxArmLimit", 95)));
    }
}
