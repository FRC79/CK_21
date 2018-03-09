package org.usfirst.frc.team79.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossAuto extends CommandGroup{
	
	public CrossAuto() {
		this.addSequential(new DriveTime(2.0,.5));
	}

}
