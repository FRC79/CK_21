package org.usfirst.frc.team79.robot.pathfinding;

import org.usfirst.frc.team79.robot.util.Conversions;

import jaci.pathfinder.Waypoint;

public class FWaypoint extends Waypoint{

	/**
	 * Converts from feet to meters and degrees to radians as a Waypoint object
	 * @param x position in feet
	 * @param y position in feet
	 * @param angle in degrees
	 */
	public FWaypoint(double x, double y, double angle) {
		super(Conversions.feetToMeters(x), Conversions.feetToMeters(y), Math.toRadians(angle));
	}

}
