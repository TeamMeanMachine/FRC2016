package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.RobotMap;

public class ZeroArmCommand extends Command {

    @Override
    protected void initialize() {
        SmartDashboard.putNumber("ArmZeroVolts", RobotMap.magnepotArm.getVoltage());
    }

    @Override
    protected void execute() {
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
