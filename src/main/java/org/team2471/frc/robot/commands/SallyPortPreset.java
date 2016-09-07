package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.Robot;

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
