/*----------------------------------------------------------------------------*/
/* Copyright (c) 2008-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Pirated by me

package org.usfirst.frc.team79.robot.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * A class for driving Mecanum drive platforms.
 *
 * <p>
 * Mecanum drives are rectangular with one wheel on each corner. Each wheel has
 * rollers toed in 45 degrees toward the front or back. When looking at the
 * wheels from the top, the roller axles should form an X across the robot. Each
 * drive() function provides different inverse kinematic relations for a Mecanum
 * drive robot.
 *
 * <p>
 * Drive base diagram:
 * 
 * <pre>
 * \\_______/
 * \\ |   | /
 *   |   |
 * /_|___|_\\
 * /       \\
 * </pre>
 *
 * <p>
 * Each drive() function provides different inverse kinematic relations for a
 * Mecanum drive robot. Motor outputs for the right side are negated, so motor
 * direction inversion by the user is usually unnecessary.
 *
 * <p>
 * This library uses the NED axes convention (North-East-Down as external
 * reference in the world frame):
 * http://www.nuclearprojects.com/ins/images/axis_big.png.
 *
 * <p>
 * The positive X axis points ahead, the positive Y axis points right, and the
 * positive Z axis points down. Rotations follow the right-hand rule, so
 * clockwise rotation around the Z axis is positive.
 *
 * <p>
 * Inputs smaller then
 * {@value edu.wpi.first.wpilibj.drive.RobotDriveBase#kDefaultDeadband} will be
 * set to 0, and larger values will be scaled so that the full range is still
 * used. This deadband value can be changed with {@link #setDeadband}.
 *
 * <p>
 * RobotDrive porting guide: <br>
 * In MecanumDrive, the right side speed controllers are automatically inverted,
 * while in RobotDrive, no speed controllers are automatically inverted. <br>
 * {@link #driveCartesian(double, double, double, double)} is equivalent to
 * {@link edu.wpi.first.wpilibj.RobotDrive#mecanumDrive_Cartesian(double, double, double, double)}
 * if a deadband of 0 is used, and the ySpeed and gyroAngle values are inverted
 * compared to RobotDrive (eg driveCartesian(xSpeed, -ySpeed, zRotation,
 * -gyroAngle). <br>
 * {@link #drivePolar(double, double, double)} is equivalent to
 * {@link edu.wpi.first.wpilibj.RobotDrive#mecanumDrive_Polar(double, double, double)}
 * if a deadband of 0 is used.
 */
public class MecanumUtil extends RobotDriveBase {

	private TalonSRX frontLeftMotor;
	private TalonSRX rearLeftMotor;
	private TalonSRX frontRightMotor;
	private TalonSRX rearRightMotor;

	/**
	 * Construct a MecanumDrive.
	 *
	 * <p>
	 * If a motor needs to be inverted, do so before passing it in.
	 */
	public MecanumUtil(TalonSRX frontLeftMotor, TalonSRX rearLeftMotor, TalonSRX frontRightMotor, TalonSRX rearRightMotor) {
		this.frontLeftMotor = frontLeftMotor;
		this.rearLeftMotor = rearLeftMotor;
		this.frontRightMotor = frontRightMotor;
		this.rearRightMotor = rearRightMotor;
	}

	/**
	 * Drive method for Mecanum platform.
	 *
	 * <p>
	 * Angles are measured clockwise from the positive X axis. The robot's speed is
	 * independent from its angle or rotation rate.
	 *
	 * @param ySpeed
	 *            The robot's speed along the Y axis [-1.0..1.0]. Right is positive.
	 * @param xSpeed
	 *            The robot's speed along the X axis [-1.0..1.0]. Forward is
	 *            positive.
	 * @param zRotation
	 *            The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise
	 *            is positive.
	 */
	public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
		driveCartesian(ySpeed, xSpeed, zRotation, 0.0);
	}

	/**
	 * Drive method for Mecanum platform.
	 *
	 * <p>
	 * Angles are measured clockwise from the positive X axis. The robot's speed is
	 * independent from its angle or rotation rate.
	 *
	 * @param ySpeed
	 *            The robot's speed along the Y axis [-1.0..1.0]. Right is positive.
	 * @param xSpeed
	 *            The robot's speed along the X axis [-1.0..1.0]. Forward is
	 *            positive.
	 * @param zRotation
	 *            The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise
	 *            is positive.
	 * @param gyroAngle
	 *            The current angle reading from the gyro in degrees around the Z
	 *            axis. Use this to implement field-oriented controls.
	 */
	public void driveCartesian(double ySpeed, double xSpeed, double zRotation, double gyroAngle) {
		ySpeed = limit(ySpeed);
		ySpeed = applyDeadband(ySpeed, m_deadband);

		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, m_deadband);

		// Compensate for gyro angle.
		Vector2d input = new Vector2d(ySpeed, xSpeed);
		input.rotate(-gyroAngle);

		double[] wheelSpeeds = new double[4];
		wheelSpeeds[MotorType.kFrontLeft.value] = input.x + input.y + zRotation;
		wheelSpeeds[MotorType.kFrontRight.value] = input.x - input.y + zRotation;
		wheelSpeeds[MotorType.kRearLeft.value] = -input.x + input.y + zRotation;
		wheelSpeeds[MotorType.kRearRight.value] = -input.x - input.y + zRotation;

		normalize(wheelSpeeds);

		frontLeftMotor.set(ControlMode.PercentOutput, wheelSpeeds[MotorType.kFrontLeft.value] * m_maxOutput);
		frontRightMotor.set(ControlMode.PercentOutput, wheelSpeeds[MotorType.kFrontRight.value] * m_maxOutput);
		rearLeftMotor.set(ControlMode.PercentOutput, wheelSpeeds[MotorType.kRearLeft.value] * m_maxOutput);
		rearRightMotor.set(ControlMode.PercentOutput, wheelSpeeds[MotorType.kRearRight.value] * m_maxOutput);

		m_safetyHelper.feed();
	}

	/**
	 * Drive method for Mecanum platform.
	 *
	 * <p>
	 * Angles are measured counter-clockwise from straight ahead. The speed at which
	 * the robot drives (translation) is independent from its angle or rotation
	 * rate.
	 *
	 * @param magnitude
	 *            The robot's speed at a given angle [-1.0..1.0]. Forward is
	 *            positive.
	 * @param angle
	 *            The angle around the Z axis at which the robot drives in degrees
	 *            [-180..180].
	 * @param zRotation
	 *            The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise
	 *            is positive.
	 */
	public void drivePolar(double magnitude, double angle, double zRotation) {
		driveCartesian(magnitude * Math.sin(angle * (Math.PI / 180.0)), magnitude * Math.cos(angle * (Math.PI / 180.0)),
				zRotation, 0.0);
	}

	@Override
	public void stopMotor() {
		frontLeftMotor.set(ControlMode.PercentOutput, 0);
		frontRightMotor.set(ControlMode.PercentOutput, 0);
		rearLeftMotor.set(ControlMode.PercentOutput, 0);
		rearRightMotor.set(ControlMode.PercentOutput, 0);
		m_safetyHelper.feed();
	}

	@Override
	public String getDescription() {
		return "MecanumDrive";
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		
	}
}
