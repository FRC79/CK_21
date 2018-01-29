package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ControlElevator extends Command {

	public ControlElevator() {
		
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		Robot.elevator.left.set(ControlMode.PercentOutput, Robot.oi.operator.getZ());
		Robot.elevator.right.set(ControlMode.PercentOutput, Robot.oi.operator.getZ());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
