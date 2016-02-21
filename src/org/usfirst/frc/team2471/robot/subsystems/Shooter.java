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
	
	public CANTalon topMotor;
	public CANTalon bottomMotor;

	protected void initDefaultCommand() {
		setDefaultCommand(new Shoot());
	}

	public Shooter() {
		topMotor = RobotMap.shootMotorTop;
		bottomMotor = RobotMap.shootMotorBottom;
		
		topMotor.changeControlMode( CANTalon.TalonControlMode.Speed );
		bottomMotor.changeControlMode( CANTalon.TalonControlMode.Speed );
		
		topMotor.setFeedbackDevice( CANTalon.FeedbackDevice.QuadEncoder );
		bottomMotor.setFeedbackDevice( CANTalon.FeedbackDevice.QuadEncoder );
		
		topMotor.reverseSensor(false);
		bottomMotor.reverseSensor(true);
		
		bottomMotor.reverseOutput(true);
		
		topMotor.configEncoderCodesPerRev( 250 );
		bottomMotor.configEncoderCodesPerRev( 250 );
		
		topMotor.configPeakOutputVoltage( 12.0, 0.0 );
		bottomMotor.configPeakOutputVoltage( 0.0, -12.0 );
		
		topMotor.configNominalOutputVoltage( 0.0, 0.0 );
		bottomMotor.configNominalOutputVoltage( 0.0, 0.0 );
		
		topMotor.setPID( 0.03, 0, 0.005 );
		bottomMotor.setPID( 0.03, 0, 0.005 );
		
		topMotor.setF( 0.120 );
		bottomMotor.setF( 0.140 );
	}

	public void shoot(double x, double y) {
		// TODO Auto-generated method stub
		RobotMap.shootMotorTop.setSetpoint(x);
		RobotMap.shootMotorBottom.setSetpoint(y);
		topMotor.enable();
		bottomMotor.enable();
		if ((x > 0.0 || x < 0.0) && (y > 0.0 || y < 0.0)){
			RobotMap.shootIntake.set(-0.80);
		}else{
			RobotMap.shootIntake.set(0.0);
		}
	}

	public void stop() {
		topMotor.disable();
		bottomMotor.disable();
		RobotMap.shootIntake.set(0.0);
	}

	public void shootLogic() {
		double x = SmartDashboard.getNumber("Top", 2700);
		double y = SmartDashboard.getNumber("Bottom", 2500);
		
		if (SmartDashboard.getBoolean("Shoot"))
		{
			Robot.shooter.shoot(x, y);
			RobotMap.ringLight.set(true);
		}
		else
		{
			Robot.shooter.stop();
			RobotMap.ringLight.set(false);
		}
		
		SmartDashboard.putNumber("TopSpeed", topMotor.getSpeed());
		SmartDashboard.putNumber("BottomSpeed", bottomMotor.getSpeed());
	}
}
