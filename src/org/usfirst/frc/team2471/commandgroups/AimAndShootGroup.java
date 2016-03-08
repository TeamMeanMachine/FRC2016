package org.usfirst.frc.team2471.commandgroups;

import org.usfirst.frc.team2471.robot.commands.Aim2;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.StartShooter;
import org.usfirst.frc.team2471.robot.commands.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimAndShootGroup extends CommandGroup {
    
    public  AimAndShootGroup(boolean finishOnTarget) {
    	
    	addSequential( new BackUntilOuterWorks() );
    	addSequential( new StartShooter() );
    	addSequential( new RotateArmToAngle(22.5));
    	
    	addSequential( new Aim2(finishOnTarget));
    	
    	addSequential( new RotateArmToAngle(62.0));
    	addSequential( new StopShooter() );    	
    }
}
