package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.OI;
import org.team2471.frc.robot.Robot;

public class DriveLoop extends Command {

    public DriveLoop() {
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double forward = -OI.driverStick.getRawAxis(1);    //Forward & Backwards
        double turn = -OI.driverStick.getRawAxis(4);     //Left & Right
        Robot.drive.useControllerInput(forward, turn);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.drive.setPower(0.0, 0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }

}
