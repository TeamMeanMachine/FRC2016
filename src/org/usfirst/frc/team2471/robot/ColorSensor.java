package org.usfirst.frc.team2471.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

public class ColorSensor {
	
	private static final int TCS34725_COMMAND_BIT   =   0x80;

	private static final int TCS34725_ENABLE = 0x00;
	private static final int TCS34725_ATIME = 0x01;    /* Integration time */
	private static final int TCS34725_WTIME = 0x03;    /* Wait time (if TCS34725_ENABLE_WEN is asserted; */
	private static final int TCS34725_AILTL = 0x04;    /* Clear channel lower interrupt threshold */
	private static final int TCS34725_AILTH = 0x05;
	private static final int TCS34725_AIHTL = 0x06;    /* Clear channel upper interrupt threshold */
	private static final int TCS34725_AIHTH = 0x07;
	private static final int TCS34725_PERS = 0x0C;    /* Persistence register - basic SW filtering mechanism for interrupts */
	private static final int TCS34725_CONFIG = 0x0D;
	private static final int TCS34725_CONTROL = 0x0F;    /* Set the gain level for the sensor */
	private static final int TCS34725_ID = 0x12;    /* 0x44 = TCS34721/TCS34725, 0x4D = TCS34723/TCS34727 */
	private static final int TCS34725_STATUS = 0x13;
	private static final int TCS34725_CDATAL = 0x14;    /* Clear channel data */
	private static final int TCS34725_CDATAH = 0x15;
	private static final int TCS34725_RDATAL = 0x16;    /* Red channel data */
	private static final int TCS34725_RDATAH = 0x17;
	private static final int TCS34725_GDATAL = 0x18;    /* Green channel data */
	private static final int TCS34725_GDATAH = 0x19;
	private static final int TCS34725_BDATAL = 0x1A;    /* Blue channel data */
	private static final int TCS34725_BDATAH = 0x1B;

	private static final int TCS34725_ENABLE_AIEN = 0x10;    /* RGBC Interrupt Enable */
	private static final int TCS34725_ENABLE_WEN = 0x08;    /* Wait enable - Writing 1 activates the wait timer */
	private static final int TCS34725_ENABLE_AEN = 0x02;    /* RGBC Enable - Writing 1 actives the ADC, 0 disables it */
	private static final int TCS34725_ENABLE_PON = 0x01;    /* Power on - Writing 1 activates the internal oscillator, 0 disables it */
	private static final int TCS34725_WTIME_2_4MS = 0xFF;    /* WLONG0 = 2.4ms   WLONG1 = 0.029s */
	private static final int TCS34725_WTIME_204MS = 0xAB;    /* WLONG0 = 204ms   WLONG1 = 2.45s  */
	private static final int TCS34725_WTIME_614MS = 0x00;    /* WLONG0 = 614ms   WLONG1 = 7.4s   */
	private static final int TCS34725_PERS_NONE = 0b0000;  /* Every RGBC cycle generates an interrupt                                */
	private static final int TCS34725_PERS_1_CYCLE = 0b0001;  /* 1 clean channel value outside threshold range generates an interrupt   */
	private static final int TCS34725_PERS_2_CYCLE = 0b0010;  /* 2 clean channel values outside threshold range generates an interrupt  */
	private static final int TCS34725_PERS_3_CYCLE = 0b0011;  /* 3 clean channel values outside threshold range generates an interrupt  */
	private static final int TCS34725_PERS_5_CYCLE = 0b0100;  /* 5 clean channel values outside threshold range generates an interrupt  */
	private static final int TCS34725_PERS_10_CYCLE = 0b0101;  /* 10 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_15_CYCLE = 0b0110;  /* 15 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_20_CYCLE = 0b0111;  /* 20 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_25_CYCLE = 0b1000;  /* 25 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_30_CYCLE = 0b1001;  /* 30 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_35_CYCLE = 0b1010;  /* 35 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_40_CYCLE = 0b1011;  /* 40 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_45_CYCLE = 0b1100;  /* 45 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_50_CYCLE = 0b1101;  /* 50 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_55_CYCLE = 0b1110;  /* 55 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_PERS_60_CYCLE = 0b1111;  /* 60 clean channel values outside threshold range generates an interrupt */
	private static final int TCS34725_CONFIG_WLONG = 0x02;    /* Choose between short and long (12x; wait times via TCS34725_WTIME */
	private static final int TCS34725_STATUS_AINT = 0x10;    /* RGBC Clean channel interrupt */
	private static final int TCS34725_STATUS_AVALID = 0x01;    /* Indicates that the RGBC channels have completed an integration cycle */
	private static final int TCS34725_CLEAR_INTERRUPT = 0x66;
	
	private boolean initialized;
	
	public static enum IntegrationTime {
		TCS34725_INTEGRATIONTIME_2_4MS(0xFF),   /**<  2.4ms - 1 cycle    - Max Count: 1024  */
		TCS34725_INTEGRATIONTIME_24MS(0xF6),   /**<  24ms  - 10 cycles  - Max Count: 10240 */
		TCS34725_INTEGRATIONTIME_50MS(0xEB),   /**<  50ms  - 20 cycles  - Max Count: 20480 */
		TCS34725_INTEGRATIONTIME_101MS(0xD5),   /**<  101ms - 42 cycles  - Max Count: 43008 */
		TCS34725_INTEGRATIONTIME_154MS(0xC0),   /**<  154ms - 64 cycles  - Max Count: 65535 */
		TCS34725_INTEGRATIONTIME_700MS(0x00);    /**<  700ms - 256 cycles - Max Count: 65535 */
		private final int time;
		private IntegrationTime(int time) {
			this.time = time;
		}
		public int getIntegrationTime() {
			return time;
		}
	}
	private IntegrationTime integrationTime;
	
	public static enum SensorGain {
		TCS34725_GAIN_1X(0x00),
		TCS34725_GAIN_4X(0x01),
		TCS34725_GAIN_16X(0x02),
		TCS34725_GAIN_60X(0x03);
		private final int gain;
		private SensorGain(int gain) {
			this.gain = gain;
		}
		public int getGain() {
			return gain;
		}
	}
	private SensorGain gain;
	
	public static class ColorData {
		public int r;
		public int g;
		public int b;
		public int c;
	}
	
	private I2C i2c;
	private DigitalOutput led;

	public ColorSensor(int ledPin, IntegrationTime _integrationTime, SensorGain _gain) {
		i2c = new I2C(I2C.Port.kOnboard, 0x29);
		led = new DigitalOutput(ledPin);
		initialized = false;
		integrationTime = _integrationTime;
		gain = _gain;
		initialize();
	}
	
	public void write8(int regAddr, int value) {
		i2c.write(TCS34725_COMMAND_BIT | regAddr, value & 0xFF);
	}
	
	public byte read8(int regAddr) {
		i2c.transaction(new byte[]{(byte)(TCS34725_COMMAND_BIT | regAddr)}, 1, null, 0);
		
		byte[] rxBuffer = new byte[1];
		i2c.transaction(null, 0, rxBuffer, 1);
		return rxBuffer[0];
	}
	
	public short read16(int regAddr) {
		i2c.transaction(new byte[]{(byte)(TCS34725_COMMAND_BIT | regAddr)}, 1, null, 0);
		
		byte[] rxBuffer = new byte[2];
		i2c.transaction(null, 0, rxBuffer, 2);
		
		short result = rxBuffer[0];
		result |= rxBuffer[1] << 8;
		return result;
	}
	
	public void enable() {
		write8(TCS34725_ENABLE, TCS34725_ENABLE_PON);
		Timer.delay(0.003);
		write8(TCS34725_ENABLE, TCS34725_ENABLE_PON | TCS34725_ENABLE_AEN);  
	}
	
	public void disable() {
		  byte reg = read8(TCS34725_ENABLE);
		  write8(TCS34725_ENABLE, reg & ~(TCS34725_ENABLE_PON | TCS34725_ENABLE_AEN));
	}
	
	public boolean initialize() {
		byte x = read8(TCS34725_ID);
		if ((x != 0x44) && (x != 0x10)) {
			return false;
		}
		initialized = true;

		setIntegrationTime(integrationTime);
		setGain(gain);

		enable();

		return true;
	}
	
	public void setIntegrationTime(IntegrationTime it) {
		if(!initialized) {
			initialize();
		}
		
		write8(TCS34725_ATIME, it.getIntegrationTime());
		integrationTime = it;
	}
	
	public void setGain(SensorGain g) {
		if(!initialized) {
			initialize();
		}
		
		write8(TCS34725_CONTROL, g.getGain());
		gain = g;
	}
	
	public ColorData getRawData() {
		if(!initialized) {
			initialize();
		}
		
		ColorData color = new ColorData();
		color.c = read16(TCS34725_CDATAL);
		color.r = read16(TCS34725_RDATAL);
		color.g = read16(TCS34725_GDATAL);
		color.b = read16(TCS34725_BDATAL);
		switch(integrationTime) {
		case TCS34725_INTEGRATIONTIME_2_4MS:
			Timer.delay(0.0024);
			break;
		case TCS34725_INTEGRATIONTIME_24MS:
			Timer.delay(0.024);
			break;
		case TCS34725_INTEGRATIONTIME_50MS:
			Timer.delay(0.050);
			break;
		case TCS34725_INTEGRATIONTIME_101MS:
			Timer.delay(0.101);
			break;
		case TCS34725_INTEGRATIONTIME_154MS:
			Timer.delay(0.154);
			break;
		case TCS34725_INTEGRATIONTIME_700MS:
			Timer.delay(0.700);
			break;
		}
		
		return color;
	}
	
	public double calculateColorTempurature(ColorData color) {
		double x, y, z;
		double xc, yc;
		double n;
		double cct;
		
		x = (-0.14282 * color.r) + (1.54924 * color.g) + (-0.95641 * color.b);
		y = (-0.32466 * color.r) + (1.57837 * color.g) + (-0.73191 * color.b);
		z = (-0.68202 * color.r) + (0.77073 * color.g) + ( 0.56332 * color.b);
		
		xc = (x) / (x + y + z);
		yc = (y) / (x + y + z);
		
		n = (xc - 0.3320) / (0.1858 - yc);
		
		cct = (449.0 * Math.pow(n, 3)) + (3525.0 * Math.pow(n, 2)) + (6823.3 * n) + 5520.33;
		
		return cct;
	}
	
	public double calculateLux(ColorData color) {
		double illuminance = (-0.32466 * color.r) + (1.57837 * color.g) + (-0.73191 * color.b);
		return illuminance;
	}
	
	public void setInterrupt(boolean i) {
		byte r = read8(TCS34725_ENABLE);
		if(i) {
			r |= TCS34725_ENABLE_AIEN;
		}
		else {
			r &= ~TCS34725_ENABLE_AIEN;
		}
		
		write8(TCS34725_ENABLE, r);
	}
	
	public void clearInterrupt() {
		i2c.transaction(new byte[]{(byte)(TCS34725_COMMAND_BIT | TCS34725_CLEAR_INTERRUPT)}, 1, null, 0);
	}
	
	public void setIntLimits(int low, int high) {
		write8(0x04, low & 0xFF);
		write8(0x05, low >> 8);
		write8(0x06, high & 0xFF);
		write8(0x07, high >> 8);
	}
	
	public void ledOn() {
		led.set(true);
	}
	
	public void ledOff() {
		led.set(false);
	}
}
