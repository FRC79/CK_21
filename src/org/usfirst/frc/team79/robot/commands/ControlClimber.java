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
		//Robot.climber.rightLeader.set(ControlMode.PercentOutput, value);
		//If nothing is working, just get rid of everything below and simply run above
		//Try to see if the limit switches are reading correctly. Press one manually and see if it trigger. Pressing one should read true while held and false while released. If not, let me know!
		System.out.println("Top: " + Robot.climber.topSwitch.get() + ", Bottom: " + Robot.climber.bottomSwitch.get());
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
