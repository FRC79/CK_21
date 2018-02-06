package org.usfirst.frc.team79.robot.pid;

import org.usfirst.frc.team79.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;

public class GyroPIDController extends PIDController {
	
	public GyroPIDController(double Kp, double Ki, double Kd) {
		super(Kp, Ki, Kd, Robot.driveTrain.gyro, new GyroPIDOutput());
		this.setName("Gyro PID");
		this.setAbsoluteTolerance(0.05);
	}
	
}
