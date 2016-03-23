package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrawBridgeHelper extends CommandGroup {
    
    public  DrawBridgeHelper() {
    	double drawBridgeAngle = SmartDashboard.getNumber("SallyPortPreset", 36.0) + 12.0;
    	addSequential(new RotateArmToAngle(drawBridgeAngle));
    	addSequential(new DriveCommand(0, 0.5), 0.25);
    	addSequential(new RotateArmToAngle(drawBridgeAngle - 5));
    	//addSequential(new DriveDistanceCommand());
    }
}
