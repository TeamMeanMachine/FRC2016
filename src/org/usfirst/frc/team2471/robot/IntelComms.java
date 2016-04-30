package org.usfirst.frc.team2471.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntelComms {
	private final int PORT = 5802;
	private double lastAimError = 0;
	private double lastBlobCount = 0;
	private Thread serverThread;
	
	private ServerSocket serverSocket;
	
	public IntelComms() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			Robot.logger.logError("Failed to create listening server. Aim WILL NOT WORK");
			SmartDashboard.putBoolean("AutoAim", false);
			return;
		}
		
		serverThread = new Thread(() -> intelThread());
		
		
		try {
			serverThread.start();
		} catch (IllegalThreadStateException e) {
			e.printStackTrace();
		}
	}
	
	private void intelThread() {
		try {
			Robot.logger.logDebug("Starting intelvision thread");
			Socket connectionSocket = serverSocket.accept();
			Robot.logger.logInfo("Socket found at port " + connectionSocket.getPort());
			while(true) {
				char charArray[] = new char[80];
				try{
					InputStreamReader inStream = new InputStreamReader(connectionSocket.getInputStream());
					BufferedReader clientInput = new BufferedReader(inStream);
					
					int numRead = clientInput.read(charArray); 
					String clientMessage = String.valueOf(charArray);
					parseInput(clientMessage);
				} catch (IOException e){
					Robot.logger.logDebug("Error in reading input " + e.getMessage());
				}
			}
		} catch (IOException e) {
			Robot.logger.logError(" Error code 2 " + e.getMessage());
		}	
	}
	
	private void parseInput(String message) {
		try {
			if(message.length() < 3) { // Because if the intelstick hasn't found any targets yet we don't want to spam the console
				return;
			}
			String[] parsedMessage = message.split(" ");
			lastBlobCount = Integer.parseInt(parsedMessage[0]);
			SmartDashboard.putNumber("BLOB_COUNT", lastBlobCount);
			
			lastAimError = Double.parseDouble(parsedMessage[1]);
			SmartDashboard.putNumber("AIM_ERROR", lastAimError);
		} catch (Exception e) {
			//Robot.logger.logError("Error code 3 " + e.getMessage());
		}
	}
	
	public double getAimError() {
		if(lastAimError == 0) {
			Robot.logger.logWarning("Aim error received is most likely invalid");
		}
		return lastAimError;
	}
}
