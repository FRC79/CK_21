package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ControlElevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public TalonSRX slave, talon;
	public DigitalInput topSwitch, bottomSwitch;
	public Counter topCounter, bottomCounter;
	
	public Elevator() {
		slave = new TalonSRX(RobotMap.leftElevatorTalon);
		talon = new TalonSRX(RobotMap.rightElevatorTalon);
		slave.set(ControlMode.Follower, RobotMap.rightElevatorTalon);
		topSwitch = new DigitalInput(RobotMap.topElevatorSwitch);
		topCounter = new Counter(topSwitch);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ControlElevator());
	}

}
