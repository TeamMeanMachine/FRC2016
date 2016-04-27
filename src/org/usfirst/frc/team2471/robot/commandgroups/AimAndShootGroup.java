package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.Aim2;
import org.usfirst.frc.team2471.robot.commands.LogCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;
import org.usfirst.frc.team2471.robot.commands.Shoot;
import org.usfirst.frc.team2471.robot.commands.StartShooter;
import org.usfirst.frc.team2471.robot.commands.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AimAndShootGroup extends CommandGroup {
    
    public  AimAndShootGroup(boolean fastMode) {
    	addSequential( new StartShooter() );
    	
    	addSequential( new RotateArmToAngle(-5.0));

    	addSequential( new Aim2(true, fastMode));

    	if(SmartDashboard.getBoolean("AutoAim", true)) {
        	addSequential( new Shoot() );    		
    	}
    	else {
        	addSequential( new LogCommand("No shoot"));    		
    	}

    	addSequential( new RotateArmToAngle(62.0));  	
    	
    }
}
