package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Robot;

public class SuckUpBall extends Command {

    private double ballInTime;
    private boolean ballIn;

    public SuckUpBall() {
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
        Robot.intake.intakeDown();
        ballInTime = 0;
        ballIn = false;
    }

    @Override
    protected void execute() {
        Robot.intake.intakeIn(1.0);
        if (!ballIn && Robot.intake.getIntakeSensor()) {
            ballInTime = Timer.getFPGATimestamp();
            ballIn = true;
        }
    }

    @Override
    protected boolean isFinished() {
        return (ballIn && (Timer.getFPGATimestamp() - ballInTime) > 0.25);
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
