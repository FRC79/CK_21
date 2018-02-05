package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	
	public ArcadeDrive() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.arcadeDrive.arcadeDrive(-Robot.oi.drive.getY(), Robot.oi.drive.getZ(), true);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
