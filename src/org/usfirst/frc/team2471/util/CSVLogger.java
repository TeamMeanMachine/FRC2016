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
	private int columns = 0;

	public CSVContext createNewContext(String name) {
		CSVContext context = new CSVContext(name, columns);
		contexts.add(context);
		columns++;
		return context;
	}

	public void tick() {
		List<CSVContext> filteredContexts = contexts.stream()
						.filter(context -> context.isActive())
						.collect(Collectors.toList());
		

	}

	public class CSVContext implements AutoCloseable {
		private boolean active = false;
		private String name;
		private int column;
		private Supplier<Number> supplier;

		private CSVContext(String name, int column) {
			this.name = name;
			this.column = column;
		}

		private String getValueAsString() {
			return Double.toString(supplier.get().doubleValue());
		}

		private void setSupplier(Supplier<Number> supplier) {
			this.supplier = supplier;
			this.active = true;
		}

		public int getColumn() {
			return column;
		}

		public boolean isActive() {
			return active;
		}

		@Override
		public void close() {
			contexts.remove(this);
		}
	}
}