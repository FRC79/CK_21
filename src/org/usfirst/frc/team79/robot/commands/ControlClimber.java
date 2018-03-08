package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ControlClimber extends Command {
	
	/**
	 * Uses the operator joystick to control the climber
	 */
	public ControlClimber() {
		requires(Robot.climber);
	}
	
	@Override
	protected void execute() {
		double value = -Robot.oi.operator.getY(Hand.kRight);
		if(value > 0) {
			if(!Robot.climber.topSwitch.get()) {
				Robot.climber.rightLeader.set(ControlMode.PercentOutput, value);
			}else Robot.climber.stopMotors();
		}else if(value < 0) {
			if(!Robot.climber.bottomSwitch.get()) {
				Robot.climber.rightLeader.set(ControlMode.PercentOutput, value);
			}else Robot.climber.stopMotors();
		}else Robot.climber.stopMotors();
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
