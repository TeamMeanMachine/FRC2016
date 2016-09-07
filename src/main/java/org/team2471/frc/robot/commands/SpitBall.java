package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Robot;

public class SpitBall extends Command {
    private double releaseTime;
    private double endTime;
    private boolean released;

    public SpitBall() {
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
        Robot.intake.intakeStop();
        Robot.intake.intakeDown();
        releaseTime = Timer.getFPGATimestamp() + 0.4;
        endTime = Timer.getFPGATimestamp() + 2.5; // To make sure it ends. It shouldn't make it this far
        released = false;
    }

    @Override
    protected void execute() {
        Robot.intake.intakeDown();
        if (Timer.getFPGATimestamp() > releaseTime) {
            Robot.intake.intakeOut(1.0);
            if (!Robot.intake.getIntakeSensor() && !released) {
                endTime = Timer.getFPGATimestamp() + 0.6;
                released = true;
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() > endTime;
    }

    @Override
    protected void end() {
        Robot.intake.intakeStop();
        Robot.intake.intakeUp();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
