package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ControlClimber extends Command {
	
	public ControlClimber() {
		requires(Robot.climber);
	}
	
	@Override
	protected void execute() {
		double value = Robot.oi.operator.getY();
		if(value >= 0) {
			Robot.climber.left.set(ControlMode.PercentOutput, value);
			Robot.climber.rightLeader.set(ControlMode.PercentOutput, value);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
