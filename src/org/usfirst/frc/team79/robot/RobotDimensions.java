package org.usfirst.frc.team79.robot;

/**
 * Contains any dimensions of the physical aspects of the robot, i.e. robot width. Comment with the unit of measurement
 */
public class RobotDimensions {
	
	/**The width of the drive train wheels inches*/
	public static final double WHEEL_WIDTH = 2;
	/**The diameter of the drive train wheels in inches*/
	public static final double WHEEL_DIAMETER = 12;
	/**The circumference of the wheel in feet*/
	public static final double WHEEL_CIRCUMFERENCE = Math.PI*(WHEEL_DIAMETER/12d);

}
