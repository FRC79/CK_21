package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class LowerElevator extends Command {

	/**
	 * Lowers the elevator
	 */
	public LowerElevator() {
	}
	
	@Override
	protected void execute() {
		Robot.elevator.talon.set(ControlMode.PercentOutput, -0.5);
	}
	
	@Override
	protected void end() {
		Robot.elevator.stopMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
