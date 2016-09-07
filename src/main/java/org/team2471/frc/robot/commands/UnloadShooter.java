package org.team2471.frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.Robot;

public class UnloadShooter extends Command {


    public UnloadShooter() {
        requires(Robot.shooter);
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
        Robot.logger.logInfo("Unloading shooter");
        Robot.intake.intakeUp();
        Robot.intake.intakeIn(1);
    }

    @Override
    protected void execute() {
        Robot.shooter.shooterIntakeReverse();
    }

    @Override
    protected boolean isFinished() {
        return Robot.intake.getIntakeSensor();
    }

    @Override
    protected void end() {
        Robot.logger.logDebug("Intake sensor: " + Robot.intake.getIntakeSensor());
        Robot.shooter.shooterIntakeOff();
        Robot.intake.intakeStop();
    }

    @Override
    protected void interrupted() {
        Robot.logger.logWarning("UnloadShooter interrupted");
    }


//	@Override
//	protected voi 

}
