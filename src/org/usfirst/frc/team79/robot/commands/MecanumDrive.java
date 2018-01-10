package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls the motors for use of mecanum drive.
 *
 */
public class MecanumDrive extends Command {
	
	public MecanumDrive() {
		
	}
	
	@Override
	public void execute() {
		Robot.driveTrain.mecDrive.driveCartesian(Robot.oi.drive.getY(), Robot.oi.drive.getX(), Robot.oi.drive.getZ());
	}
	
	@Override
	protected void end() {
		Robot.driveTrain.mecDrive.stopMotor();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
