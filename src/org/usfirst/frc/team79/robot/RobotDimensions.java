package org.usfirst.frc.team79.robot;

/**
 * Contains any dimensions of the physical aspects of the robot, i.e. robot width. Comment with the unit of measurement
 */
public class RobotDimensions {
	
	/**The width of the drive train wheels inches*/
	public static final double WHEEL_WIDTH = 1;
	/**The diameter of the drive train wheels in inches*/
	public static final double WHEEL_DIAMETER = 6;
	/**The circumference of the wheel in inches*/
	public static final double WHEEL_CIRCUMFERENCE = Math.PI*(WHEEL_DIAMETER);
	
	
	/**The amount of encoder ticks per 1 revolution*/
	public static final int TICKS_PER_REV = 1200; //TODO
}
