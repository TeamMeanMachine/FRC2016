package org.usfirst.frc.team2471.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.Timer;


public class CSVLogger {
	private List<DoubleSupplier> methods = new ArrayList<>();
	private List<String> buffer = new ArrayList<>();
	private Thread stepThread;
	private double refreshTime;
	private final String SAVE_PATH = "/home/lvuser/aim.csv";
	
	public CSVLogger() {
		this.stepThread = new Thread(new StepThread());
		this.stepThread.start();
		this.refreshTime = Timer.getFPGATimestamp() + 1;
		
		buffer.add("Timestamp,Key,Value");
	}
	
	public void addToBuffer(String key, double value) {
		StringBuilder lineBuilder = new StringBuilder();
		lineBuilder.append(getTimestamp());
		lineBuilder.append(",");
		
		lineBuilder.append(key);
		lineBuilder.append(",");
		
		lineBuilder.append(value);
		lineBuilder.append("\n");
		buffer.add(lineBuilder.toString());
		
	}
	
	private double getTimestamp() {
		return Math.round(Timer.getFPGATimestamp() * 100) / 100;
	}
	
	private class StepThread implements Runnable {
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					Robot.logger.logWarning("CSVLogger interrupted!");
				}
				
				if(Timer.getFPGATimestamp() >= refreshTime) {
					if(buffer.size() > 0) {
						Path filePath = Paths.get(SAVE_PATH);
						try {
							if(!Files.exists(filePath)) {
								Files.createFile(filePath);
							}
							Files.write(filePath, buffer, Charset.forName("UTF-8"), StandardOpenOption.WRITE);
						} catch (IOException e) {
							Robot.logger.logError("Error in updating CSV");
							return; 
						}
					}
				}
			}
			
		}
		
	}
}
