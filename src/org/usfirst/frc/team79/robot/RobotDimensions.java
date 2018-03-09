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
	
	/**
	 * The width of the starting configuration of the robot in inches
	 */
	public static final double ROBOT_WIDTH = 34.21;
	
	/**
	 * The height of the starting configuration of the robot in inches
	 */
	public static final double ROBOT_HEIGHT = 66;
	
	/**
	 * The lenth of the starting configuration of the robot in inches
	 */
	public static final double ROBOT_LENGTH = 46.21;

}
