package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.Constants;
import org.team2471.frc.robot.OI;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.RobotMap;

/**
 *
 */
public class Aim3 extends PIDCommand { // This is all broken dont use
    private double offsetAngle;
    private boolean finished = false;
    private int samples = 0;
    private boolean findingSample = true;
    private double newSampleTimestamp = Timer.getFPGATimestamp();
    private double currentSampleTimestamp;

    public Aim3(boolean finishOnTarget) { // TODO: Use boolean
        super(Constants.AIM_2_P, Constants.AIM_2_I, Constants.AIM_2_D);
        requires(Robot.drive);
        requires(Robot.shooter);
    }


    @Override
    protected void initialize() {
        Robot.drive.setAimDrop(true);
    }


    @Override
    protected void execute() { // TODO: Add manual aim
        SmartDashboard.putNumber("GyroSetPoint", getPIDController().getSetpoint());
        if (findingSample) {
            if (RobotMap.gyro.getRate() < 0.5 && Timer.getFPGATimestamp() > newSampleTimestamp) {
                calcNewSample();
            }
        } else {
            if (onTarget()) {
                Robot.logger.logDebug("Sample found after " + Math.round((Timer.getFPGATimestamp() - currentSampleTimestamp) / 100) + " ms");
                findingSample = true;
            }
        }
//		Robot.shooter.shootLogic();
    }

    @Override
    protected boolean isFinished() {
        return finished || OI.coStick.getRawButton(2);
    }

    @Override
    protected void end() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub

    }

    @Override
    protected double returnPIDInput() {
        return RobotMap.gyro.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drive.setAimerMotor(output);
    }

    private void calcNewSample() {
        if (SmartDashboard.getNumber("BLOB_COUNT") > 0.0) {
            findingSample = false; // So we don't keep finding new samples
            samples++;
            currentSampleTimestamp = Timer.getFPGATimestamp();
            if (Math.abs(SmartDashboard.getNumber("AIM_ERROR", 0.0)) < 1.0) {
                finished = true;
                Robot.logger.logInfo("Robot is on target!");
                return;
            }
            Robot.logger.logInfo("Processing sample " + samples);

            offsetAngle = SmartDashboard.getNumber("AIM_ERROR");
            Robot.logger.logDebug("Setting gyro to " + RobotMap.gyro.getAngle() + offsetAngle + "\nCurrent angle is " + RobotMap.gyro.getAngle());
            setSetpoint(RobotMap.gyro.getAngle() + offsetAngle);

        } else {
            newSampleTimestamp = Timer.getFPGATimestamp() + 1; // Wait and try again
            Robot.logger.logDebug("Blob not found! Trying again in 1 second");
        }
    }

    private boolean onTarget() {
        return Math.abs(getPIDController().getError()) < 0.1;
    }

}
