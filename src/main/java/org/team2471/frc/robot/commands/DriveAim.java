package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.OI;
import org.team2471.frc.robot.Robot;

/**
 * A different version of manual aim where instead of using the aimdrop we pivot with the
 * drive train. This exists so we can demo the robot's aiming when we aren't able to use the aimdrop
 * in robot demos.
 */
public class DriveAim extends Command {

    public DriveAim() {
        requires(Robot.drive);
        requires(Robot.shooter);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double deadband = 0.1;
        double power = Math.abs(OI.coStick.getRawAxis(4)) > deadband ? OI.coStick.getRawAxis(4) : 0; // Apply deadband
        power *= 0.6; // Scale down
        Robot.drive.setPower(power, 0);
    }

    @Override
    protected boolean isFinished() {
        return OI.coStick.getRawButton(2) || OI.coStick.getRawButton(6);
    }

    @Override
    protected void end() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

}
