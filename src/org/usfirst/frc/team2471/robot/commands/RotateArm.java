package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.OI;
import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateArm extends Command {
	private int testNumber;
	
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
		testNumber = 0;
	}

	@Override
	protected void execute() {
		double upDownValue = -OI.coStick.getRawAxis(1);
		
		double deadband = 0.2;
		if (Math.abs(upDownValue) < deadband)  // dead band for xbox
			upDownValue = 0.0;
		else
			upDownValue = (upDownValue - Math.signum(upDownValue)*deadband) / (1.0-deadband);
		
		upDownValue = upDownValue * upDownValue * upDownValue;
		
		upDownValue *= 2.0;
		
		double armAngle = Robot.defenseArm.getTargetAngle();
		armAngle += upDownValue;
		
		Robot.defenseArm.setTargetAngle(armAngle);
		
		testNumber++;
		if(testNumber % 20 == 0) {
			SmartDashboard.putNumber("Defense Arm Position", armAngle);
			SmartDashboard.putNumber("Defense Arm Error", Robot.defenseArm.getPIDController().getError());
			SmartDashboard.putNumber("Defense Arm Voltage", RobotMap.magnepotArm.getVoltage());
		}
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
