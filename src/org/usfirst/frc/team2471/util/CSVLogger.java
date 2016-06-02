package org.usfirst.frc.team2471.util;

import edu.wpi.first.wpilibj.Timer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class CSVLogger {
	private List<CSVContext> contexts = new ArrayList<>();
	private List<String> outBuffer = new ArrayList<>();

	public CSVContext createNewContext(String name) {
		CSVContext context = new CSVContext(name, columns);
		return context;
	}

	public void tick() {
		outBuffer.add(contexts.stream()
		                      .map(context -> context.getStringOutput())
		                      .collect(joining (","))); // TODO: make sure this compiles
	}

	public class CSVContext implements AutoCloseable {
		private boolean active = false;
		private String name;
		private int column;
		private Supplier<Number> supplier;

		@Override
		public void close() {
			this.active = false;
		}
		
		private CSVContext(String name, int column) {
			this.name = name;
			this.column = column;
		}

		private String getStringOutput() {
			if(active) {
				return Double.toString(supplier.get().doubleValue());
			}
			return "";
		}

		private void setSupplier(Supplier<Number> supplier) {
			this.supplier = supplier;
			active = true;
		}

		private int getColumn() {
			return column;
		}
	}
}