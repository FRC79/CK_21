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
		Robot.climber.rightLeader.set(ControlMode.PercentOutput, -Robot.oi.operator.getRawAxis(3));
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
