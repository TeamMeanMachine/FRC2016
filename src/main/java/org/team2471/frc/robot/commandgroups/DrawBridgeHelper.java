package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.commands.DriveCommand;
import org.team2471.frc.robot.commands.DriveDistanceCommand;
import org.team2471.frc.robot.commands.RotateArmToAngle;

/**
 *
 */
public class DrawBridgeHelper extends CommandGroup {

    public DrawBridgeHelper() {
        double drawBridgeAngle = SmartDashboard.getNumber("DrawBridgePreset", 47.0);
        addSequential(new RotateArmToAngle(drawBridgeAngle));
        addSequential(new DriveCommand(0, 1.0), 0.25);
        addSequential(new WaitCommand(0.25));
        addSequential(new RotateArmToAngle(drawBridgeAngle - 10));
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveDistanceCommand(0.5, 0, -0.5));
        addParallel(new RotateArmToAngle(drawBridgeAngle - 30));
        addSequential(new DriveDistanceCommand(1.2, 0, -0.5));
        addParallel(new RotateArmToAngle(0));
        addSequential(new DriveDistanceCommand(0.3, 0, -0.5));
        addSequential(new RotateArmToAngle(-10));
        addSequential(new WaitCommand(0.5));
        addSequential(new RotateArmToAngle(-15));
        addSequential(new DriveDistanceCommand(1, 0, 0.5));
        addSequential(new RotateArmToAngle(10));
    }
}
