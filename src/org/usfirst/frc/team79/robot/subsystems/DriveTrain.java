package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ArcadeDrive;
import org.usfirst.frc.team79.robot.util.ArcadeUtil;
import org.usfirst.frc.team79.robot.util.MecanumUtil;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for components responsible for moving the robot
 *
 */
public class DriveTrain extends Subsystem {
	
	public TalonSRX frontLeft, frontRight, backLeft, backRight;
	/**
	 * Used for mecanum driving. Called in MecanumDrive command.
	 */
	public MecanumUtil mecDrive;
	/** Used for arcade driving. Calling in ArcadeDrive command*/
	public ArcadeUtil arcadeDrive;
	
	public DriveTrain() {
		frontLeft = new TalonSRX(RobotMap.frontLeftTalon);
		frontRight = new TalonSRX(RobotMap.frontRightTalon);
		backLeft = new TalonSRX(RobotMap.backLeftTalon);
		backRight = new TalonSRX(RobotMap.backRightTalon);
		
		backLeft.set(ControlMode.Follower, RobotMap.frontLeftTalon);
		backRight.set(ControlMode.Follower, RobotMap.frontRightTalon);
		
		frontRight.setInverted(true);
		
		arcadeDrive = new ArcadeUtil(frontLeft, frontRight);
	}
	
	public int getRightPos() {
		return frontRight.getSensorCollection().getQuadraturePosition();
	}
	
	public int getLeftPos() {
		return frontLeft.getSensorCollection().getQuadraturePosition();
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ArcadeDrive());
	}

	public void stopMotors() {
		frontLeft.set(ControlMode.PercentOutput, 0);
		frontRight.set(ControlMode.PercentOutput, 0);
	}

}
