package org.team2471.frc.util;

import edu.wpi.first.wpilibj.DigitalInput;

public class InvertedDigitalInput extends DigitalInput {
    public InvertedDigitalInput(int channel) {
        super(channel);
    }

    @Override
    public boolean get() {
        return !super.get();
    }

}
