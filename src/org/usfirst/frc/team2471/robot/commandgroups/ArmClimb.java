package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.ArmNoLimit;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmClimb extends CommandGroup {
    
    public  ArmClimb() {
    	addSequential(new ArmNoLimit(SmartDashboard.getNumber("DefenseArmClimb", 107.0)));
    	addSequential(new WaitCommand(.5));
    	addSequential(new ArmNoLimit(SmartDashboard.getNumber("SuperMaxArmLimit", 95)));
    }
}
