/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team79.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static int frontLeftTalon = 1;
	public static int frontRightTalon = 2;
	public static int backLeftTalon = 3;
	public static int backRightTalon = 4;
	public static int leftElevatorTalon = 5;
	public static int rightElevatorTalon = 6;
	public static int leftIntakeTalon = 7;
	public static int rightIntakeTalon = 8;
	public static int climberTalon1 = 9;
	public static int climberTalon2 = 10;
	public static int climberTalon3 = 11;
}
