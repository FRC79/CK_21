package org.usfirst.frc.team79.robot.util;

/**
 * Determines the side of the field. Used for switch/scale location and starting position.
 */
public enum Side {
	
	LEFT, RIGHT, MIDDLE;
	
	public static Side fromChar(char c) {
		switch(c) {
		case 'L':
			return LEFT;
		case 'R':
			return RIGHT;
		default:
			return MIDDLE;
		}
	}
	
	public char toChar() {
		switch(this) {
		case LEFT:
			return 'L';
		case RIGHT:
			return 'R';
		default: 
			return 'M';
		}
	}

}
