package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	
	/**
	 * The main command for driving the robot via the driver controllers
	 */
	public ArcadeDrive() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.arcadeDrive(-Robot.oi.drive.getY(Hand.kLeft), 0.9*Robot.oi.drive.getX(Hand.kLeft));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
