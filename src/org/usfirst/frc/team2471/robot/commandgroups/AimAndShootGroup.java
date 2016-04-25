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
    
    public  AimAndShootGroup(boolean finishOnTarget) {
    	addSequential( new LogCommand("StartShooter"));
    	addSequential( new StartShooter() );
    	
    	addSequential( new LogCommand("RotateArmToAngle"));
    	addSequential( new RotateArmToAngle(-5.0));

    	addSequential( new LogCommand("Aim2"));
    	addSequential( new Aim2(finishOnTarget));

    	if(SmartDashboard.getBoolean("AutoAim", true)) {
        	addSequential( new LogCommand("Shoot"));
        	addSequential( new Shoot() );    		
    	}
    	else {
        	addSequential( new LogCommand("No shoot"));    		
    	}

    	addSequential(new LogCommand("WaitCommand"));
    	addSequential( new WaitCommand(1.0));  
    	
    	addSequential(new LogCommand("StopShooter"));
    	addSequential( new StopShooter() );
    	
    	addSequential( new LogCommand("RotateArmToAngle"));
    	addSequential( new RotateArmToAngle(62.0));  	
    	
    }
}
