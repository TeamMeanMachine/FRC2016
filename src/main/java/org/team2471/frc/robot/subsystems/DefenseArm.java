package org.team2471.frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.Constants;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.RobotMap;
import org.team2471.frc.robot.commands.RotateArm;

public class DefenseArm extends PIDSubsystem {

    private CANTalon armMotor;
    private CANTalon armMotor2; // Right motor. Temporary
    private AnalogInput magnePot;
    private double targetAngle;


    public DefenseArm() {
        super(Constants.DEFENSE_P, Constants.DEFENSE_I, Constants.DEFENSE_D);
        armMotor = RobotMap.defenseArm;
        armMotor2 = RobotMap.defenseArmRight;
        magnePot = RobotMap.magnepotArm;
//		armMotor.setVoltageRampRate(13.0);  // 13.0 volts per second
        setTargetAngle(getPosition());
        enable();
        SmartDashboard.putData("DefenseArmPID", getPIDController());
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RotateArm());
    }

    public void rotate(double power) {
        if (RobotMap.pdp.getCurrent(8) <= 40 && RobotMap.pdp.getCurrent(7) <= 40) {
            armMotor.set(power);
            armMotor2.set(-power);
        } else {
            Robot.logger.logWarning("Arm tried to go over amperage limit!");
            setSetpoint(getPosition());
            armMotor.set(0.0);
            armMotor2.set(0);
        }
    }

    @Override
    protected double returnPIDInput() {
        double rValue = voltageToAngle(magnePot.getVoltage());
        return rValue;
    }

    @Override
    protected void usePIDOutput(double output) {
        rotate(output);
    }

    public double angleToVoltage(double angle) {
        double zeroVolts = SmartDashboard.getNumber("ArmZeroVolts", 2.314);
        angle /= (360.0 / 4.6);
        return angle + zeroVolts;
    }

    public double voltageToAngle(double voltage) {
        //What I think the conversion should be (according to the MagnePot datasheet)
        double zeroVolts = SmartDashboard.getNumber("ArmZeroVolts", 2.314) - 0.2; //Comp bot is 2.339 || Practice is 2.22
        voltage -= 0.2;
        voltage -= zeroVolts;
        double angle = voltage * (360.0 / 4.6);

        return angle;
    }

    public void setTargetAngle(double angle) {
        double minAngle = SmartDashboard.getNumber("DefenseArmMin", -13.0);
        double maxAngle;
        if (Robot.climbing) {
            maxAngle = SmartDashboard.getNumber("SuperMaxArmLimit", 95.0);
        } else {
            maxAngle = SmartDashboard.getNumber("DefenseArmMax", 77.0);
        }

        if (angle > maxAngle) {
            angle = maxAngle;
        } else if (angle < minAngle) {
            angle = minAngle;
        }
        targetAngle = angle;
        setSetpoint(targetAngle);
    }

    public double getTargetError() {
        return targetAngle - getPosition();
    }

    public double getTargetAngle() {
        return targetAngle;
    }
}
