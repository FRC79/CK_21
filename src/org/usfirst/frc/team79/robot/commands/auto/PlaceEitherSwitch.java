package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.commands.IntakeOut;
import org.usfirst.frc.team79.robot.commands.LiftElevator;
import org.usfirst.frc.team79.robot.util.Side;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceEitherSwitch extends CommandGroup{
	
	/**
	 * From the middle wall, places a block on either the left or right switch
	 * @param swtch which side the switch is on
	 */
	public PlaceEitherSwitch(Side swtch) {
		this.addSequential(new DriveDistance(47.477));
		if(swtch==Side.LEFT) {
			this.addSequential(new RotateEncoders(-90));
			this.addSequential(new DriveDistance(39.52));
			this.addSequential(new RotateEncoders(90));
		}else {
			this.addSequential(new RotateEncoders(90));
			this.addSequential(new DriveDistance(22.847));
			this.addSequential(new RotateEncoders(-90));
		}
		this.addSequential(new DriveDistance(47.2));
		this.addSequential(new LiftElevator(2));
		this.addSequential(new IntakeOut(2));
	}

}
