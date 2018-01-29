package org.usfirst.frc.team79.robot.subsystems;


import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ControlClimber;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Climber extends Subsystem{

	public TalonSRX motor1, motor2, motor3;
	
	public Climber() {
		motor1 = new TalonSRX(RobotMap.climberTalon1);
		motor2 = new TalonSRX(RobotMap.climberTalon2);
		motor3 = new TalonSRX(RobotMap.climberTalon3);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ControlClimber());
	}

	
	
}
