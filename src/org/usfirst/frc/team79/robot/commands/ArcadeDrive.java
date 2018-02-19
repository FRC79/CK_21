package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	
	public double fixGyro;
	
	public ArcadeDrive() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		fixGyro = 0;
	}
	
	@Override
	protected void execute() {
		if(Math.abs(Robot.oi.drive.getX()) > 0) {
			Robot.driveTrain.arcadeDrive(-Robot.oi.drive.getY(), 0.9*Robot.oi.drive.getX());
			fixGyro = Robot.driveTrain.gyro.getAngle();
		}else {
			Robot.driveTrain.driveStraight(-Robot.oi.drive.getY(), fixGyro);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
