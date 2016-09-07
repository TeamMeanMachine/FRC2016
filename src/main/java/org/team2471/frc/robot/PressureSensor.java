package org.team2471.frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class PressureSensor {
    private AnalogInput sensor;

    public PressureSensor(int channel) {
        sensor = new AnalogInput(channel);
    }

    public double getPressure() {
        return 250.0 * (sensor.getVoltage() / 5.0) - 25.0;
    }
}
