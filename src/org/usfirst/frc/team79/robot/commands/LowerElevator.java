package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class LowerElevator extends Command {

	public LowerElevator() {
		
	}
	
	@Override
	protected void initialize() {
		Robot.elevator.bottomCounter.reset();
	}
	
	@Override
	protected void execute() {
		Robot.elevator.talon.set(ControlMode.PercentOutput, -0.5);
	}
	
	@Override
	protected void end() {
		Robot.elevator.bottomCounter.reset();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.elevator.bottomCounter.get() > 0;
	}

}
