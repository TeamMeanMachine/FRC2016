package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SallyPortPreset extends RotateArmToAngle {

    public SallyPortPreset() {
    	super(SmartDashboard.getNumber("SallyPortPreset", 36.0));
    }
}
