package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Constants;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AimDropTest extends PIDCommand {
	private double angle;
	private PIDController controller;

    public AimDropTest() {
		super(Constants.AIM_2_P, Constants.AIM_2_I, Constants.AIM_2_D);
		requires(Robot.drive);
		requires(Robot.shooter);
		
		this.angle = SmartDashboard.getNumber("AIM_DROP_TEST_ANGLE");
		this.controller = getPIDController();
		this.controller.setAbsoluteTolerance(1);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setSetpoint(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.drive.setAimDrop(true);
		Robot.shooter.shootLogic();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return controller.onTarget();
    }
    

	@Override
	protected double returnPIDInput() {
		return RobotMap.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setAimerMotor(output);
		// Robot.drive.setSpeed(-output, 0.0); // run the drivetrain instead
	}

    // Called once after isFinished returns true
    protected void end() {
		controller.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
