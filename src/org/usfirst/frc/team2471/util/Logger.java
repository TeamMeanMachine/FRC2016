package org.usfirst.frc.team2471.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logger implements Closeable {
	private File logFile;
	private PrintWriter logWriter;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("hh.mm.ss");

	public Logger(){
		StringBuilder logFileNameBuilder = new StringBuilder("LOG_");
	    logFileNameBuilder.append(getTimestamp(true));
	    logFileNameBuilder.append(".txt");
	    String fileName = logFileNameBuilder.toString();

		this.logFile = new File(fileName);
		// Create log file
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Failed to create logger file!");
			e.printStackTrace();
		}
		
		// Open print writer
		try {
			this.logWriter = new PrintWriter(this.logFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		logInfo("Logger initialized!");
	}
	
	private String getTimestamp(boolean includeDate) {
	    Date today = Calendar.getInstance().getTime();
	    if(includeDate) {
	    	return formatter.format(today);
	    }
	    else {
	    	return dateFormatter.format(today);
	    }
	    
	}
	
	public void logDebug(String message) {
		logInfo("DEBUG", message, true, false);
	}
	public void logInfo(String message) {
		logInfo("INFO", message, true, true);
	}
	public void logWarning(String message) {
		logInfo("WARNING", message, true, true);
	}
	public void logSevere(String message) {
		logInfo("SEVERE", message, true, true);
	}
	
	private void logInfo(String level, String message, boolean printToConsole, boolean writeToFile) {
		StringBuilder messageBuilder = new StringBuilder("[");
		messageBuilder.append(level);
		messageBuilder.append("] (");
		messageBuilder.append(getTimestamp(false));
		messageBuilder.append("): ");
		messageBuilder.append(message);
		messageBuilder.append("\n");
		String finalMessage = messageBuilder.toString();
		
		if(printToConsole) {
			System.out.println(finalMessage);
		}
		if(writeToFile) {
			logWriter.write(finalMessage);
		}
	}

	@Override
	public void close() throws IOException {
		logWriter.flush();
		this.logWriter.close();
	}
}