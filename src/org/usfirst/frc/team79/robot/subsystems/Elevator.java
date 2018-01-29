package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ControlElevator;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public TalonSRX left, right;
	
	public Elevator() {
		left = new TalonSRX(RobotMap.leftElevatorTalon);
		right = new TalonSRX(RobotMap.rightElevatorTalon);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ControlElevator());
	}

}
