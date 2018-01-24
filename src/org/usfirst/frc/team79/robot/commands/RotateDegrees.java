package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.Robot;
import org.usfirst.frc.team79.robot.pid.GyroPIDController;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class RotateDegrees extends Command {

	public GyroPIDController gyro;
	public double displacement;
	
	public RotateDegrees(double displacement) {
		gyro = Robot.gyroPID;
		this.displacement = displacement;
	}
	
	@Override
	protected void initialize() {
		gyro.setSetpoint(Robot.driveTrain.gyro.getAngle()+this.displacement);
		gyro.enable();
	}
	
	@Override
	protected void end() {
		gyro.disable();
		Robot.driveTrain.frontLeft.set(ControlMode.PercentOutput, 0);
		Robot.driveTrain.frontRight.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return gyro.onTarget() && gyro.isEnabled();
	}

}
