package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveCommand;
import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrawBridgeHelper extends CommandGroup {
    
    public  DrawBridgeHelper() {
    	double drawBridgeAngle = 70;
    	addSequential(new RotateArmToAngle(drawBridgeAngle));
    	addSequential(new DriveCommand(0, 0.5), 0.5);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new RotateArmToAngle(drawBridgeAngle - 10));
    	addSequential(new WaitCommand(0.5));
    	addParallel(new RotateArmToAngle(drawBridgeAngle - 30));    	
    	addSequential(new DriveDistanceCommand(2, 0, -0.5));
    	addParallel(new RotateArmToAngle(0));    	
    	addSequential(new DriveDistanceCommand(1, 0, -0.25));
    }
}
