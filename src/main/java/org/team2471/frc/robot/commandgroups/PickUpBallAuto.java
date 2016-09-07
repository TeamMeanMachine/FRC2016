package org.team2471.frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.frc.robot.Constants;
import org.team2471.frc.robot.OI;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.commands.CenterBall;
import org.team2471.frc.robot.commands.IntakeDown;
import org.team2471.frc.robot.commands.QueueShot;
import org.team2471.frc.robot.commands.RumbleJoystick;

/**
 *
 */
public class PickUpBallAuto extends CommandGroup {

    public PickUpBallAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.

        addSequential(new IntakeDown());
        addParallel(new RumbleJoystick(0.5, OI.driverStick));
        addSequential(new CenterBall(), 1);

        addSequential(new MicroBallHelper());
        addSequential(new MicroBallHelper());
        addSequential(new MicroBallHelper());
//    	addSequential(new MicroBallHelper()); commented because robot looks nicer only centering once
        if (Constants.DEPEND_ON_SHOOTER_SENSOR) {
            addSequential(new QueueShot(), 2);
        }
    }

    @Override
    protected void end() {
        Robot.intake.setSuckCanceled(false);
    }
}
