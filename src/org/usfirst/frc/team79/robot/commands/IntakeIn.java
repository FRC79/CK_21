package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeIn extends Command{
	
	public float time;
	private boolean doTime;
	private Timer timer;
	
	/**
	 * Intake the 'cube'
	 */
	public IntakeIn() {
		time = -1;
	}
	
	public IntakeIn(float time) {
		this.time = time;
	}
	
	@Override
	protected void initialize() {
		if(time >= 0) {
			doTime = true;
		}
		timer = new Timer();
		timer.start();
	}
	
	@Override
	protected void execute() {
		Robot.intake.leftMotor.set(ControlMode.PercentOutput , .8);
		Robot.intake.rightMotor.set(ControlMode.PercentOutput, .8);
//		Robot.oi.operator.setRumble(RumbleType.kLeftRumble, 1);
	}
	
	@Override
	protected void end() {
		Robot.oi.operator.setRumble(RumbleType.kLeftRumble, 0);
		Robot.intake.stopMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return doTime && timer.hasPeriodPassed(time);
	}

}
