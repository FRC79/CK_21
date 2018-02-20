package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LiftElevator extends Command {
	
	public boolean doTime;
	public double time;
	public Timer timer;
	
	/**
	 * Lifts the elevator
	 */
	public LiftElevator() {
		doTime = false;
		time = -1;
	}
	
	/**
	 * Lifts the elevator for a certain amount of time
	 * @param time in seconds
	 */
	public LiftElevator(double time) {
		doTime = true;
		this.time = time;
	}
	
	@Override
	protected void initialize() {
		timer = new Timer();
	}
	
	@Override
	protected void execute() {
		Robot.elevator.talon.set(ControlMode.PercentOutput, 0.5);
	}
	
	@Override
	protected void end() {
		Robot.elevator.stopMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return doTime && timer.hasPeriodPassed(time);
	}

}
