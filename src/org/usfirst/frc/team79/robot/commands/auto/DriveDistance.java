package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.Robot;
import org.usfirst.frc.team79.robot.RobotDimensions;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command{
	
	public double distance;

	/**
	 * Drive straight for a specified distance
	 * @param distance in inches
	 */
	public DriveDistance(double distance) {
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.resetEncoders();
		Robot.driveTrain.gyro.reset();
	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.driveStraight(0.4, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.getRightInches() >= distance;
	}

}
