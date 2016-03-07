package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpitOutEmergency extends Command{

	
	public SpitOutEmergency() {
		// TODO Auto-generated constructor stub
		requires(Robot.intake);
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.intake.intakeDown();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.shooterIntakeReverse();
		Robot.intake.intakeOut(.8);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.intake.intakeUp();
		Robot.intake.intakeStop();
		Robot.shooter.shooterIntakeOff();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}
	
	

}
