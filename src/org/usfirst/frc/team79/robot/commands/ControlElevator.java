package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ControlElevator extends Command {

	public ControlElevator() {
		requires(Robot.elevator);
	}
	
	@Override
	protected void initialize() {
		Robot.elevator.topCounter.reset();
		Robot.elevator.bottomCounter.reset();
	}
	
	@Override
	protected void execute() {
		Robot.elevator.talon.set(ControlMode.PercentOutput, Robot.oi.operator.getZ());
	}
	
	@Override
	protected void end() {
		Robot.elevator.topCounter.reset();
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.topCounter.get()>0 || Robot.elevator.bottomCounter.get() > 0;
	}
	
}
