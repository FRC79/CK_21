package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.commands.IntakeOut;
import org.usfirst.frc.team79.robot.commands.LiftElevator;
import org.usfirst.frc.team79.robot.util.Side;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceScale extends CommandGroup{
	
	/**
	 * From the left or right wall, places a block on the scale
	 * @param scale Which side the scale is on
	 * @param wall Which wall you're on
	 */
	public PlaceScale(Side scale, Side wall) {
		if(wall==Side.LEFT) {
			//From the left wall
			if(scale==Side.LEFT) {
				//To the left scale
				this.addSequential(new DriveDistance(300.395));
				this.addSequential(new RotateEncoders(90));
				this.addSequential(new DriveDistance(36.895));
			}else {
				//To the right scale
				this.addSequential(new DriveDistance(216));
				this.addSequential(new RotateEncoders(90));
				this.addSequential(new DriveDistance(225.895));
				this.addSequential(new RotateEncoders(-90));
				this.addSequential(new DriveDistance(61.395));
			}
		}else {
			//From the right wall
			if(scale==Side.LEFT) {
				//To the left scale
				this.addSequential(new DriveDistance(212));
				this.addSequential(new RotateEncoders(-90));
				this.addSequential(new DriveDistance(228.5));
				this.addSequential(new RotateEncoders(90));
				this.addSequential(new DriveDistance(68.5));
			}else {
				//To the right scale
				this.addSequential(new DriveDistance(216));
				this.addSequential(new RotateEncoders(-90));
				this.addSequential(new DriveDistance(225.895));
				this.addSequential(new RotateEncoders(90));
				this.addSequential(new DriveDistance(61.395));
			}
		}
		this.addSequential(new LiftElevator(6));
		this.addSequential(new IntakeOut(2));
	}

}
