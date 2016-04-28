package org.usfirst.frc.team2471.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntelComms {
	private final int PORT = 5802;
	private double lastAimError = 0;
	
	private ServerSocket serverSocket;
	
	public IntelComms() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			Robot.logger.logError("Failed to create listening server. Aim WILL NOT WORK");
			SmartDashboard.putBoolean("AutoAim", false);
			return;
		}
		new Thread(new ServerThread()).start();
	}
	
	private class ServerThread implements Runnable {
		@Override
		public void run() {
			try {
				Socket connectionSocket = serverSocket.accept();
				while(true) {
					BufferedReader clientInput = 
							new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
					String clientMessage = clientInput.readLine();
					parseInput(clientMessage);
					
				}
			} catch (IOException e) {
				Robot.logger.logError(e.getMessage());
			}
		}		
	}
	
	private void parseInput(String message) {
		try {
			lastAimError = Double.parseDouble(message);
		} catch (NumberFormatException e) {
			Robot.logger.logWarning("Received packet cannot be converted to a number. Message is " + message);
		}
	}
	
	public double getAimError() {
		if(lastAimError == 0) {
			Robot.logger.logWarning("Aim error received is most likely invalid");
		}
		return lastAimError;
	}
}
