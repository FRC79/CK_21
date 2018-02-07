package org.usfirst.frc.team79.robot.pathfinding;

import java.io.File;
import java.io.IOException;import org.opencv.utils.Converters;
import org.usfirst.frc.team79.robot.RobotDimensions;
import org.usfirst.frc.team79.robot.util.Conversions;

import edu.wpi.first.wpilibj.RobotDrive;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class MotionProfileManager {
	
	//We're just going to make all of these units in inches.
	//These units were chosen from what was in the example.
	public static Config config = new Config(FitMethod.HERMITE_CUBIC, Config.SAMPLES_LOW, 0.05, 47.2441, 78.7402, 2362.2);
	public static Trajectory leftPath, rightPath;
	
	public static void generate(String autoName, Waypoint... points) {
		File pathFileLeft = new File("/home/lvuser/motionPaths/" + autoName + "Left.csv");
		File pathFileRight = new File("/home/lvuser/motionPaths/" + autoName + "Right.csv");
		try {
			pathFileLeft.createNewFile();
			pathFileRight.createNewFile();
		} catch (IOException e) {
			System.err.println("Failed to create new files for saving motion profile.");
			e.printStackTrace();
			return;
		}
		TankModifier tank = new TankModifier(Pathfinder.generate(points, config)).modify(RobotDimensions.WHEEL_WIDTH);
		leftPath = tank.getLeftTrajectory();
		rightPath = tank.getRightTrajectory();
		Pathfinder.writeToCSV(pathFileLeft, leftPath);
		Pathfinder.writeToCSV(pathFileRight, rightPath);
	}
	
	/**
	 * Loads the saved motion paths into the leftPath and rightPath fields.
	 */
	public static void load(String autoName) {
		File pathFileLeft = new File("/home/lvuser/motionPaths/"+ autoName + "Left.csv");
		File pathFileRight = new File("/home/lvuser/motionPaths/" + autoName + "Right.csv");
		leftPath = Pathfinder.readFromCSV(pathFileLeft);
		rightPath = Pathfinder.readFromCSV(pathFileRight);
	}

}
