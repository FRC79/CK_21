package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.commands.IntakeOut;
import org.usfirst.frc.team79.robot.commands.LiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Autonomous command for placing a Power "Cube" on the center scale
 *
 */
public class PlaceScaleMP extends CommandGroup {
	
	/**
	 * Attempts to autonomously place a block on the scale during autonomous period
	 * @param wall The left or right side of the field
	 * @param side A single character representing which scale is ours
	 */
	public PlaceScaleMP(String wall, char side) {
		this.addParallel(new LiftElevator(6));
		this.addSequential(new RunMotionProfile(wall+"WallScale"+side));
		this.addSequential(new WaitCommand(0.5));
		this.addSequential(new IntakeOut(2));
	}
	
}