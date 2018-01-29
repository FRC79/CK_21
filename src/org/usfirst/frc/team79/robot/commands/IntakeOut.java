package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeOut extends Command{
	
	@Override
	protected void execute() {
		Robot.intake.leftMotor.set(ControlMode.PercentOutput , -.8);
		Robot.intake.rightMotor.set(ControlMode.PercentOutput, -.8);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
