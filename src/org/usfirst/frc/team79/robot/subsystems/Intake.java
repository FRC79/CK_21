package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem  {

	public TalonSRX leftMotor, rightMotor;
	
	public Intake(){
		leftMotor = new TalonSRX(RobotMap.leftIntakeTalon);
		rightMotor = new TalonSRX(RobotMap.rightIntakeTalon);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void stopMotors() {
		leftMotor.set(ControlMode.PercentOutput, 0);
		rightMotor.set(ControlMode.PercentOutput, 0);
	}

}
