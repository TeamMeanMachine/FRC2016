package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.commands.DriveDistanceCommand;
import org.team2471.frc.robot.commands.RotateArmToAngle;
import org.team2471.frc.robot.commands.RotateArmToAngleWait;

public class ChevalHelper extends CommandGroup {
    /**
     * This command should be executed when the driver is within range of the cheval de frise
     */
    public ChevalHelper() {
        // Move arm down to cheval
        addSequential(new RotateArmToAngleWait(SmartDashboard.getNumber("DefenseArmMinimum", -13), 2), 1);
        // Wait a tiny bit
        addSequential(new WaitCommand(0.5));
        // Drive forward
        addParallel(new DriveDistanceCommand(7.0, 0, 0.8));
        // Wait before moving arm up
        addSequential(new WaitCommand(1));
        // Move arm back up
        addSequential(new RotateArmToAngle(10));
    }
}
