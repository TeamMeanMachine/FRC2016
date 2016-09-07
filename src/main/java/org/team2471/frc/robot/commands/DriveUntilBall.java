package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Robot;

public class DriveUntilBall extends Command {
    private double power;

    public DriveUntilBall(double power) {
        requires(Robot.drive);
        requires(Robot.intake);
        this.power = power;
    }


    @Override
    protected void initialize() {
        Robot.drive.setPower(0, power);
        Robot.intake.intakeDown();
        Robot.intake.intakeIn(1);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return Robot.intake.getUltrasonic() < 0.4;
    }

    @Override
    protected void end() {
        Robot.drive.setPower(0, 0);
        Robot.intake.intakeUp();
        Robot.intake.intakeStop();
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

}
