package org.usfirst.frc.team2471.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Logger {
	private List<String> lines;
	private String fileName;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-hh.mm.ss");
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("hh.mm.ss");
	private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
	
	private final String LOG_PATH = "/home/lvuser/2471_logs";
		

	public Logger(){
		// Create log directory if it doesn't exist
		File logDir = new File(LOG_PATH);
		if(!logDir.exists()) {
			System.out.println("Creating log directory");
			try {
				logDir.mkdir();
			} catch(SecurityException e) {
				System.out.println("Unable to create log directory");
			}
		}
		
		StringBuilder logFileNameBuilder = new StringBuilder(LOG_PATH + "/");
	    logFileNameBuilder.append(getTimestamp(true));
	    logFileNameBuilder.append(".txt");
	    this.fileName = logFileNameBuilder.toString();
	    this.lines = new ArrayList<>();
	    this.lines.add("**BEGIN LOG FOR " + getTimestamp(true) + " **\n\n");
	    System.out.println("Logfile will be created at " + this.fileName);
		logDebug("Logger Initialized");
	}
	
	private String getTimestamp(boolean includeDate) {
	    Date today = calendar.getTime();
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
		logInfo("INFO", message, false, true);
	}
	public void logWarning(String message) {
		logInfo("WARNING", message, true, true);
	}
	public void logError(String message) {
		logInfo("ERROR", message, true, true);
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
			lines.add(finalMessage);
		}
	}

	public void update() throws IOException {
		Path file = Paths.get(fileName);
		Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		System.out.println("Log file updated");
		lines = new ArrayList<>();
	}
}