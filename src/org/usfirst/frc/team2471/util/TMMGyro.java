package org.usfirst.frc.team2471.util;

import org.usfirst.frc.team2471.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SPI.Port;

public class TMMGyro extends AHRS {
	private double previousHeading;
	private double previousTime;
	private double rate;
	
	private Thread stepThread;

	public TMMGyro(Port spi_port_id) {
		super(spi_port_id);
		this.previousHeading = getAngle();
		this.previousTime = Timer.getFPGATimestamp();
		this.rate = 0;
		this.stepThread = new Thread(new StepRunnable());
		this.stepThread.start();		
	}
	
	@Override
	public double getRate() {
		return rate;
	}
	
	@Override
	public void reset() {
		super.reset();
		System.out.println("Resetting gyro");
		rate = 0;
		previousHeading = 0;
		previousTime = Timer.getFPGATimestamp() - 1.0/20; 
	}

	
	private class StepRunnable implements Runnable {
		@Override
		public void run() {
			while(true) {
				double currentHeading = getAngle();
				double currentTime = Timer.getFPGATimestamp();
				if(currentTime != previousTime) { // We never want to divide by 0
					currentTime += 0.1;
				}
				rate = (currentHeading - previousHeading) / (currentTime - previousTime);
				
				previousHeading = currentHeading;
				previousTime = currentTime;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					Robot.logger.logWarning("Gyro thread interrupted!");
				}
			}
		}
	}
}
