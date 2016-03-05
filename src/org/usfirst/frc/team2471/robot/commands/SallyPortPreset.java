package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SallyPortPreset extends Command {

    public SallyPortPreset() {
    	requires(Robot.defenseArm);
    }

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.defenseArm.setTargetAngle(SmartDashboard.getNumber("SallyPortPreset", 36.0));
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
