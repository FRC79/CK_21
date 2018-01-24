package org.usfirst.frc.team79.robot.pid;

import org.usfirst.frc.team79.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDOutput;

public class GyroPIDOutput implements PIDOutput {
	
	@Override
	public void pidWrite(double output) {
		Robot.driveTrain.frontLeft.set(ControlMode.PercentOutput, output);
		Robot.driveTrain.frontRight.set(ControlMode.PercentOutput, output);
	}
	
}
