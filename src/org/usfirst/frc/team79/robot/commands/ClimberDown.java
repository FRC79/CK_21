package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberDown extends Command{

	public ClimberDown() {
	}
	
	@Override
	protected void execute() {
		Robot.climber.rightLeader.set(ControlMode.PercentOutput, -0.8);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
