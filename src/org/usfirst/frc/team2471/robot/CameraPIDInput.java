package org.usfirst.frc.team2471.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraPIDInput implements PIDSource, PIDOutput {
	double testVar = 100;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		double blobCount = SmartDashboard.getNumber("BLOB_COUNT", -1.0d);
		if (blobCount == -1.0d) {
			System.out.println("Connection to compute stick failed");
			testVar++;
		}
		else if (SmartDashboard.getNumber("BLOB_COUNT") > 0) {
			testVar = SmartDashboard.getNumber("AIM_ERROR");
		}
		else {
			testVar = 150.0;
		}
		SmartDashboard.putNumber("PID", testVar);
		return testVar;
	}

	@Override
	public void pidWrite(double output) {
		SmartDashboard.putNumber("PID2", output);
		Robot.drive.aimer.set(output);
		
	}

}
