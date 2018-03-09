package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.Robot;
import org.usfirst.frc.team79.robot.RobotDimensions;
import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.commands.ArcadeDrive;
import org.usfirst.frc.team79.robot.util.ArcadeUtil;

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
	
	/** Used for arcade driving. Calling in ArcadeDrive command*/
	public ArcadeUtil arcadeUtil;
	
	/**
	 * Contains all the talons (and the gyro) necessary for driving the robot.
	 */
	public DriveTrain() {
		frontLeft = new TalonSRX(RobotMap.frontLeftTalon);
		frontRight = new TalonSRX(RobotMap.frontRightTalon);
		backLeft = new TalonSRX(RobotMap.backLeftTalon);
		backRight = new TalonSRX(RobotMap.backRightTalon);
		
		backLeft.set(ControlMode.Follower, RobotMap.frontLeftTalon);
		backRight.set(ControlMode.Follower, RobotMap.frontRightTalon);
		
		arcadeUtil = new ArcadeUtil(frontLeft, frontRight);
		
		gyro = new ADXRS450_Gyro();
	}
	
	/**
	 * Move the robot using a forward value and a rotation value
	 * @param forward -1 to 1 positive is forward
	 * @param rotate -1 to 1 positive is clockwise
	 */
	public void arcadeDrive(double forward, double rotate) {
		arcadeUtil.arcadeDrive(forward, rotate, true);
	}
	
	/**
	 * Use the encoders to fix the robot in a straight path
	 * @param forward -1 to 1 positive is forward
	 * @param fixedAngle the angle to fix the robot at
	 */
	public void driveStraight(double forward, double fixedAngle) {
		double difference = getRightInches() - getLeftInches();
		double gain = 1 + 0.2*difference; //Alter the 0.2 to affect how much the difference in encoder values will matter
		frontLeft.set(ControlMode.PercentOutput, gain*forward);
		frontRight.set(ControlMode.PercentOutput, forward/gain);
	}
	
	/**
	 * Gets the value of the right encoder
	 * @return position in native encoder units
	 */
	public int getRightPos() {
		return frontRight.getSensorCollection().getQuadraturePosition();
	}
	
	/**
	 * Gets the value of the right encoder
	 * @return position in inches
	 */
	public double getRightInches() {
		return getRightPos()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	/**
	 * Gets the value of the left encoder
	 * @return position in native encoder units
	 */
	public int getLeftPos() {
		return frontLeft.getSensorCollection().getQuadraturePosition();
	}
	
	/**
	 * Gets the value of the left encoder
	 * @return position in inches
	 */
	public double getLeftInches() {
		return getLeftPos()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	/**
	 * Gets the velocity measured by the right encoder
	 * @return velocity in native encoder units per 100ms
	 */
	public int getRightVel() {
		return frontRight.getSensorCollection().getQuadratureVelocity();
	}
	
	/**
	 * Gets the velocity measured by the right encoder
	 * @return velocity in inches per 100ms
	 */
	public double getRightVelInches() {
		return getRightVel()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	/**
	 * Gets the velocity measured by the left encoder
	 * @return velocity in native encoder units per 100ms
	 */
	public int getLeftVel() {
		return frontLeft.getSensorCollection().getQuadratureVelocity();
	}
	
	/**
	 * Gets the velocity measured by the left encoder
	 * @return velocity in inches per 100ms
	 */
	public double getLeftVelInches() {
		return getLeftVel()/RobotDimensions.TICKS_PER_REV*RobotDimensions.WHEEL_CIRCUMFERENCE;
	}
	
	/**
	 * Resets the encoder values to 0
	 */
	public void resetEncoders() {
		frontRight.getSensorCollection().setQuadraturePosition(0, 0);
		frontLeft.getSensorCollection().setQuadraturePosition(0, 0);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ArcadeDrive());
	}
	
	/**
	 * Stops all of the drive motors
	 */
	public void stopMotors() {
		frontLeft.set(ControlMode.PercentOutput, 0);
		frontRight.set(ControlMode.PercentOutput, 0);
	}

}
