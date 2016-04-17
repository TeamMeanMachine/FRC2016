package org.usfirst.frc.team2471.robot.commandgroups;

import org.usfirst.frc.team2471.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team2471.robot.commands.RotateArmToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PortcullisHelper extends CommandGroup {

	/**
	 * This command should be executed when the wheels are against the slope of the outer works
	 */
	public PortcullisHelper() {
		double upAngle = SmartDashboard.getNumber("DefenseArmMax", 62);
		double downAngle = SmartDashboard.getNumber("DefenseArmMin", -16);
		addParallel(new RotateArmToAngle(downAngle));
		addSequential(new DriveDistanceCommand(1.5, 0, 0.25), 2.5);
		addParallel(new RotateArmToAngle(upAngle));
		addSequential(new WaitCommand(0.35));
		addSequential(new DriveDistanceCommand(4.5, 0, 0.8));
	}
}
