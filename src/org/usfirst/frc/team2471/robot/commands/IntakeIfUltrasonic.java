package org.usfirst.frc.team2471.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2471.robot.Robot;

/**
 * Created by tyler on 4/27/16.
 */
public class IntakeIfUltrasonic extends Command { // I think I can get a better name than this
    private double sampleTime;
    private boolean finished = false;
    private boolean foundBall = false;
    private Command driveCommand; // I am sure there is a more elegant way to do this

    public IntakeIfUltrasonic() {
        requires(Robot.intake);
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        Robot.intake.intakeDown();
        Robot.intake.intakeIn(1);
        sampleTime = Timer.getFPGATimestamp() + 0.5;
    }


    @Override
    protected void execute() {
        if(Timer.getFPGATimestamp() < sampleTime) {
            return;
        }

        if(!foundBall) {
            double sample = Robot.intake.getBallUltrasonic();
            if (sample < 6) { // TODO: Get a real number for this
                foundBall = true;
                driveCommand = new DriveWithHeading(0.5, -0.25); // TODO: Experiment with distance
            } else {
                finished = true;
            }
        }
    }

    @Override
    protected boolean isFinished() {
        if(foundBall) {
            if(!driveCommand.isRunning()) { // Not sure if this is necessary but I don't want a null pointer exception
                return true;
            }
        }
        return finished || isTimedOut();
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
