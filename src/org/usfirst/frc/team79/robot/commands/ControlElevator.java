package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ControlElevator extends Command {

	public ControlElevator() {
		requires(Robot.elevator);
	}
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		double value = Math.copySign(Math.pow(Robot.oi.operator.getY(Hand.kLeft), 2), -Robot.oi.operator.getY(Hand.kLeft));
		if(value > 0 && !Robot.elevator.topSwitch.get()){
			Robot.elevator.talon.set(ControlMode.PercentOutput, value);
		}else if(value < 0 && !Robot.elevator.bottomSwitch.get()){
			Robot.elevator.talon.set(ControlMode.PercentOutput, value);
		}else Robot.elevator.talon.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	protected void end() {
		Robot.elevator.stopMotors();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
