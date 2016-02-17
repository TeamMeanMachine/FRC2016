package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.Shoot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{
	
	public CANTalon motor1;
	public CANTalon motor2;

	protected void initDefaultCommand() {
		setDefaultCommand(new Shoot());
	}

	public Shooter(){
		motor1 = RobotMap.shootMotorTop;
		motor2 = RobotMap.shootMotorBottom;
		
		motor1.changeControlMode(CANTalon.TalonControlMode.Speed);
		motor2.changeControlMode(CANTalon.TalonControlMode.Speed);
		
		motor1.configEncoderCodesPerRev(250);
		motor2.configEncoderCodesPerRev(250);
		
		motor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		motor2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		
		motor1.setPID(0, 0, 0);
		motor2.setPID(0, 0, 0);
		
		//motor1.setF()
	}
	

	public void shoot(double x, double y) {
		// TODO Auto-generated method stub
		RobotMap.shootMotorTop.set(x);
		RobotMap.shootMotorBottom.set(y);
		
		if ((x > 0.0 || x < 0.0) && (y > 0.0 || y < 0.0)){
			RobotMap.shootIntake.set(-.4);
		}else{
			RobotMap.shootIntake.set(0.0);
		}
	}

	public void stop() {
		// TODO Auto-generated method stub
//		RobotMap.left2.set(0.0);
//		RobotMap.left3.set(0.0);
		RobotMap.shootIntake.set(0.0);
		RobotMap.shootMotorTop.set(0.0);
		RobotMap.shootMotorBottom.set(0.0);
	}

	public void shootLogic() {
		double x = SmartDashboard.getNumber("Top", .6);
		double y = SmartDashboard.getNumber("Bottom", -.4);
		
		if (SmartDashboard.getBoolean("Shoot"))
		{
			Robot.shooter.shoot(x, y);
		} else
		{
			Robot.shooter.stop();
		}		
	}
}
