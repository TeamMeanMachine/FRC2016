package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleJoystick extends Command {

    private double startTime, rumbleTime;
    private Joystick joystick;

    public RumbleJoystick(double _rumbleTime, Joystick _joystick) {
        rumbleTime = _rumbleTime;
        joystick = _joystick;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        joystick.setRumble(Joystick.RumbleType.kLeftRumble, 1.0f);
        joystick.setRumble(Joystick.RumbleType.kRightRumble, 1.0f);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > rumbleTime;
    }

    // Called once after isFinished returns true
    protected void end() {
        joystick.setRumble(Joystick.RumbleType.kLeftRumble, 0.0f);
        joystick.setRumble(Joystick.RumbleType.kRightRumble, 0.0f);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
