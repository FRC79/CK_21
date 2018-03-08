package org.usfirst.frc.team79.robot.subsystems;


import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ControlClimber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Climber extends Subsystem{

	public TalonSRX left, rightLeader;
	public DigitalInput topSwitch, bottomSwitch;
	
	/**
	 * Contains the motors for the climber
	 */
	public Climber() {
		left = new TalonSRX(RobotMap.climberLeftTalon);
		rightLeader = new TalonSRX(RobotMap.climberRightTalon);
		
		topSwitch = new DigitalInput(RobotMap.topClimberSwitch);
		bottomSwitch = new DigitalInput(RobotMap.bottomClimberSwitch);
		
		left.set(ControlMode.Follower, RobotMap.climberRightTalon);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ControlClimber());
	}

	public void stopMotors() {
		rightLeader.set(ControlMode.PercentOutput, 0);
	}
	
}
