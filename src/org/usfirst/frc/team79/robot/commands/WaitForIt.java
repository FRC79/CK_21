package org.usfirst.frc.team79.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitForIt extends Command{
	
	public Timer timer;
	public double time;
	
	/**
	 * Does nothing
	 * @param time in seconds
	 */
	public WaitForIt(double time) {
		this.time = time;
	}
	
	@Override
	protected void initialize() {
		this.timer = new Timer();
		this.timer.start();
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}

}
