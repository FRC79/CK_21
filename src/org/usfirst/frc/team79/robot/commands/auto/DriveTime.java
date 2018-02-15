package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTime extends Command{

	public Timer timer;
	public double time;
	
	public DriveTime(double time) {
		this.time = time;
	}
	
	@Override
	protected void initialize() {
		this.timer = new Timer();
	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.frontLeft.set(ControlMode.PercentOutput, 0.5);
		Robot.driveTrain.frontRight.set(ControlMode.PercentOutput, 0.5);
	}
	
	@Override
	protected void end() {
		Robot.driveTrain.stopMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}

}
