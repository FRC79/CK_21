package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.util.Side;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceSwitch extends CommandGroup {
	
	public PlaceSwitch(Side swtch, Side wall) {
		if(wall==Side.LEFT) {
			//From the left wall
			if(swtch==Side.LEFT) {
				//To the left switch
				this.addSequential(new DriveDistance(145.208));
				this.addSequential(new RotateDegrees(90));
				this.addSequential(new DriveDistance(29.895));
			}else {
				//Just cross the auto line
				this.addSequential(new CrossAuto());
			}
		}else {
			//From the right wall
			if(swtch==Side.RIGHT) {
				//To the right switch
				this.addSequential(new DriveDistance(145.208));
				this.addSequential(new RotateDegrees(-90));
				this.addSequential(new DriveDistance(29.895));
			}else {
				//Just cross the auto line
				this.addSequential(new CrossAuto());
			}
		}
	}

}
