package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.Aim2;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.Shoot;
import org.usfirst.frc.team2471.robot.commands.StartShooter;
import org.usfirst.frc.team2471.robot.commands.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AimAndShootGroup extends CommandGroup {
    
    public  AimAndShootGroup(boolean finishOnTarget) {
    	addSequential( new StartShooter() );
    	addSequential( new RotateArmToAngle(-5.0));
    	
    	addSequential( new Aim2(finishOnTarget));
    	
    	addSequential( new Shoot() );
    	
    	addSequential( new WaitCommand(1.0));  
    	addSequential( new StopShooter() );   	
    	addSequential( new RotateArmToAngle(62.0));  	
    	
    }
}
