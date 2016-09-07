package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.commands.DriveUntilUltrasonic;

public class BackUntilOuterWorks extends CommandGroup {

    public BackUntilOuterWorks(double power) {
        double ultrasonicLimit = SmartDashboard.getNumber("UltrasonicLimit", 5);
        addSequential(new DriveUntilUltrasonic(-power, ultrasonicLimit, true), 2);
//    	addSequential(new DriveUntilUltrasonic(0.3, ultrasonicLimit, false));
//    	addSequential(new DriveDistanceCommand(3.0/12, 0, 0.3));

    }
}
