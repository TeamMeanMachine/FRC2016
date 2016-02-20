package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.DefenseArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class RotateArm extends Command{

	public RotateArm() {
		requires(Robot.defenseArm);
	}
	@Override
	protected void initialize() {
    	//DefenseArm.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
	}

	@Override
	protected void execute() {
		double upDownValue = -Robot.oi.coStick.getRawAxis(1);
		
		if (Math.abs(upDownValue) < 0.15)  // dead band for xbox
			upDownValue = 0.0;
		
		if (upDownValue > 0.0)  // diminish power
			upDownValue *= 0.7;  
		else
			upDownValue *= 0.5;
			
		Robot.defenseArm.rotate( upDownValue );
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}
}
