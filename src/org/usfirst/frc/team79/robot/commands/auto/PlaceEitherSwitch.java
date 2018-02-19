package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.commands.IntakeOut;
import org.usfirst.frc.team79.robot.commands.LiftElevator;
import org.usfirst.frc.team79.robot.util.Side;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceEitherSwitch extends CommandGroup{
	
	public PlaceEitherSwitch(Side swtch) {
		this.addSequential(new DriveDistance(47.477));
		if(swtch==Side.LEFT) {
			this.addSequential(new RotateDegrees(-90));
			this.addSequential(new DriveDistance(39.52));
			this.addSequential(new RotateDegrees(90));
		}else {
			this.addSequential(new RotateDegrees(90));
			this.addSequential(new DriveDistance(22.847));
			this.addSequential(new RotateDegrees(-90));
		}
		this.addSequential(new DriveDistance(47.2));
		this.addSequential(new LiftElevator(true));
		this.addSequential(new IntakeOut(2));
	}

}
