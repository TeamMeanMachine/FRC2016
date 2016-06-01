package org.usfirst.frc.team2471.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.Timer;


public class CSVLogger {
	private List<Context> contexts = new LinkedList<>();
	
	public void addContext(String name, Supplier<Number> function) {
		contexts.add(new Context(name, function));
	}
	
	public void tick() {
		String output = contexts.stream()
		                        .reduce("", (prev, next) -> prev + next.getValue() + "\n");
	}

	private class Context {
		private String name;
		private Supplier<Number> function;

		public Context(String name, Supplier<Number> function) {
			this.name = name;
			this.function = function;
		}
	}
}