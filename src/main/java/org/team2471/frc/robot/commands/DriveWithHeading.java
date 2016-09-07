package org.team2471.frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.team2471.frc.robot.Constants;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.RobotMap;

public class DriveWithHeading extends DriveDistanceCommand {
    private double headingAngle;

    private PIDController rotateController = new PIDController(Constants.ROTATE_P, Constants.ROTATE_I, Constants.ROTATE_D,
            new RotateSource(), new RotateOutput());


    /**
     * Functionally identical to DriveDistanceCommand except with a PID controller to correct the heading to a passed in angle.
     * If no heading is specified then the default will be set to 0
     */
    public DriveWithHeading(double distance, double speed, double headingAngle) {
        super(distance, 0, speed);
        Robot.logger.logDebug("Setpoint should be set to " + headingAngle);
        this.headingAngle = headingAngle;
    }


    /**
     * Functionally identical to DriveDistanceCommand except with a PID controller to correct the heading to a passed in angle.
     * If no heading is specified then the default will be set to 0
     */
    public DriveWithHeading(double distance, double speed) {
        this(distance, speed, 0);
    }

    @Override
    protected void initialize() {
        super.initialize(); // In case we add something later
        Robot.logger.logDebug("Starting angle: " + RobotMap.gyro.getAngle());
        Robot.logger.logDebug("Setpoint: " + rotateController.getSetpoint());
        rotateController.setSetpoint(headingAngle);
        rotateController.enable();
    }

    @Override
    protected void end() {
        super.end();
        rotateController.disable();
    }

    private class RotateSource implements PIDSource {

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return RobotMap.gyro.getPIDSourceType();
        }

        @Override
        public double pidGet() {
            return RobotMap.gyro.getAngle();
        }
    }

    private class RotateOutput implements PIDOutput {
        @Override
        public void pidWrite(double output) {
            x = -output;
        }
    }


}
