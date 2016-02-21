package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.DefenseArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateArm extends Command{
	CANTalon defenseArm = RobotMap.defenseArmLeft;
	
	public RotateArm() {
		requires(Robot.defenseArm);
	}
	@Override
	protected void initialize() {
//		defenseArm.changeControlMode( CANTalon.TalonControlMode.Position );
//    	defenseArm.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot); // There is a chance an AnalogEncoder could be correct
//		defenseArm.reverseSensor(false);
//		defenseArm.setPID( 100, 0, 0 );
//		defenseArm.disable();
	}

	@Override
	protected void execute() {
		double upDownValue = -Robot.oi.coStick.getRawAxis(1);
		
		if (Math.abs(upDownValue) < 0.2)  // dead band for xbox
			upDownValue = 0.0;
		
		upDownValue = upDownValue * upDownValue * upDownValue;
		
		upDownValue *= 2.0;
		
		double armAngle = Robot.defenseArm.getTargetAngle();
		armAngle += upDownValue;
		
		if (armAngle > 72) {
			armAngle = 72;
		}
		else if (armAngle < -16) {
			armAngle = -16;
		}
		
		Robot.defenseArm.setTargetAngle(armAngle);
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
