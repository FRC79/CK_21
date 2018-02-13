package org.usfirst.frc.team79.robot.lights;

public enum DataType {
	
	TEXT(0), IMAGE(1), ALLIANCE(2);
	
	public byte id;
	
	private DataType(int id) {
		this.id = (byte) id;
	}

}
