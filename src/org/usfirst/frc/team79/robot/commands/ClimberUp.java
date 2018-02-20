package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberUp extends Command{
	
	/**
	 * Pulls the climber up
	 */
	public ClimberUp() {
	}
	
	@Override
	protected void execute() {
		Robot.climber.rightLeader.set(ControlMode.PercentOutput, 0.6);
	}
	
	@Override
	protected void end() {
		Robot.climber.stopMotors();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
