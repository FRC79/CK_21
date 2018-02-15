package org.usfirst.frc.team79.robot.subsystems;


import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ControlClimber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Climber extends Subsystem{

	public TalonSRX left, right;
	
	public Climber() {
		left = new TalonSRX(RobotMap.climberRightTalon);
		right = new TalonSRX(RobotMap.climberLeftTalon);
		
		left.set(ControlMode.Follower, RobotMap.climberLeftTalon);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ControlClimber());
	}

	
	
}
