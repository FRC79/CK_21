package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for components responsible for moving the robot
 *
 */
public class DriveTrain extends Subsystem {
	
	public TalonSRX frontLeft, frontRight, backLeft, backRight;
	
	public DriveTrain() {
		frontLeft = new TalonSRX(RobotMap.frontLeftTalon);
		frontRight = new TalonSRX(RobotMap.frontRightTalon);
		backLeft = new TalonSRX(RobotMap.backLeftTalon);
		backRight = new TalonSRX(RobotMap.backRightTalon);
		
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new MecanumDrive());
	}

}
