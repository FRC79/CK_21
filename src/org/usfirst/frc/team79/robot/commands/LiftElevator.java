package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class LiftElevator extends Command {
	
	public LiftElevator() {
		
	}
	
	@Override
	protected void initialize() {
		Robot.elevator.topCounter.reset();
	}
	
	@Override
	protected void execute() {
		Robot.elevator.talon.set(ControlMode.PercentOutput, 0.5);
	}
	
	@Override
	protected void end() {
		Robot.elevator.topCounter.reset();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.elevator.topCounter.get() > 0;
	}

}
