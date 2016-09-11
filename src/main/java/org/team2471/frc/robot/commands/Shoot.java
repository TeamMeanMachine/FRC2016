package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Constants;
import org.team2471.frc.robot.Robot;

public class Shoot extends Command {
    private double endTime;

    public Shoot() {
        requires(Robot.shooter);
    }

    @Override
    protected void initialize() {
        endTime = Timer.getFPGATimestamp() + (Constants.DEPEND_ON_SHOOTER_SENSOR ? 1.0 : 1.75);
    }

    @Override
    protected void execute() {
        if (!Constants.DEPEND_ON_SHOOTER_SENSOR) {
            Robot.intake.intakeOut(0.7);
        }
        Robot.shooter.shooterIntakeOn();
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() > endTime;
    }

    @Override
    protected void end() {
        Robot.shooter.stop();
        Robot.intake.intakeStop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
