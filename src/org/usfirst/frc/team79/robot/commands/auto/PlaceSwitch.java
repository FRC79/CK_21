package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.commands.IntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Autonomous command for placing a Power "Cube" on the alliance switch
 *
 */
public class PlaceSwitch extends CommandGroup{
	
	/**
	 * Attempts to autonomously place a block on the switch during autonomous period
	 * @param wall The left or right side of the field
	 * @param side A single character representing which switch is ours
	 */
	public PlaceSwitch(String wall, char side) {
		this.addSequential(new RunMotionProfile(wall+"WallSwitch"+side));
		this.addSequential(new WaitCommand(0.5));
		this.addSequential(new IntakeOut(2));
	}

}
