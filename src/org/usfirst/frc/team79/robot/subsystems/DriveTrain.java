package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.Robot;
import org.usfirst.frc.team79.robot.RobotDimensions;
import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ArcadeDrive;
import org.usfirst.frc.team79.robot.util.ArcadeUtil;
import org.usfirst.frc.team79.robot.util.MecanumUtil;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for components responsible for moving the robot
 *
 */
public class DriveTrain extends Subsystem {
	
	public TalonSRX frontLeft, frontRight, backLeft, backRight;
	
	public ADXRS450_Gyro gyro;
	
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
		
		arcadeDrive = new ArcadeUtil(frontLeft, frontRight);
		
		gyro = new ADXRS450_Gyro();
	}
	
	public void arcadeDrive(double forward, double rotate) {
		arcadeDrive.arcadeDrive(forward, rotate, true);
	}
	
	public void driveStraight(double forward, double fixedAngle) {
		double difference = fixedAngle - Robot.driveTrain.gyro.getAngle();
		double value = Math.abs(difference)>0.25 ? difference * 0.1 : 0;
		Robot.driveTrain.arcadeDrive(0.6, value);
	}
	
	public int getRightPos() {
		return frontRight.getSensorCollection().getQuadraturePosition();
	}
	
	public double getRightInches() {
		return getRightPos()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	public int getLeftPos() {
		return frontLeft.getSensorCollection().getQuadraturePosition();
	}
	
	public double getLeftInches() {
		return getLeftPos()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	public int getRightVel() {
		return frontRight.getSensorCollection().getQuadratureVelocity();
	}
	
	public double getRightVelInches() {
		return getRightVel()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	public int getLeftVel() {
		return frontLeft.getSensorCollection().getQuadratureVelocity();
	}
	
	public double getLeftVelInches() {
		return getLeftVel()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	public void resetEncoders() {
		frontRight.getSensorCollection().setQuadraturePosition(0, 0);
		frontLeft.getSensorCollection().setQuadraturePosition(0, 0);
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
