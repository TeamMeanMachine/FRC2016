package org.team2471.frc.robot;

public final class Constants {
    public static final boolean AIM_DROP_DISABLED = true; // Turns with drive train instead of aim drop
    public static final boolean DEPEND_ON_SHOOTER_SENSOR = false; // set to false when demoing outdoors
    public static final boolean CLIMB_DISABLED = true;
    //Auto aimer
    public static final double AIM_P = 0.0125;
    public static final double AIM_I = 0.0;
    public static final double AIM_D = 0.00001;

    public static final double AIM_2_P = 0.2;
    public static final double AIM_2_I = 0.0;
    public static final double AIM_2_D = 0.0008;

    public static final double DRIVE_P = 0.0025; // For demoing
    public static final double DRIVE_I = 0.0000;
    public static final double DRIVE_D = 0.0025;

//    public static final double DRIVE_P = 0.005;
//    public static final double DRIVE_I = 0.001;
//    public static final double DRIVE_D = 0.03;

    //Defense arm
    public static final double DEFENSE_P = 0.05;
    public static final double DEFENSE_I = 0.0;
    public static final double DEFENSE_D = 0.007;

    //Shooter
    public static final double SHOOTER_P = 0.00004;
    public static final double SHOOTER_I = 0.0;
    public static final double SHOOTER_D = 0.00025;

    //Turning
    public static final double TURN_P = 0.001;
    //	public static final double TURN_I_AUTO = 0.005;
    public static final double TURN_I_AUTO = 0.000;
    public static final double TURN_I_TELEOP = 0.0;
    public static final double TURN_D = 0.001;

    //Rotate
    public static final double ROTATE_P = 0.1;
    public static final double ROTATE_I = 0.0;
    public static final double ROTATE_D = 0.1;
}
