/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.subsystems.Climber;
import org.usfirst.frc.team79.robot.commands.auto.CrossAuto;
import org.usfirst.frc.team79.robot.commands.auto.PlaceEitherSwitch;
import org.usfirst.frc.team79.robot.commands.auto.PlaceScale;
import org.usfirst.frc.team79.robot.commands.auto.PlaceSwitch;
import org.usfirst.frc.team79.robot.commands.auto.RunMotionProfile;
import org.usfirst.frc.team79.robot.pathfinding.MotionProfileManager;
import org.usfirst.frc.team79.robot.pid.GyroPIDController;
import org.usfirst.frc.team79.robot.subsystems.DriveTrain;
import org.usfirst.frc.team79.robot.subsystems.Elevator;
import org.usfirst.frc.team79.robot.subsystems.Intake;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Waypoint;
import org.usfirst.frc.team79.robot.util.Side;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static Intake intake;
	public static Climber climber;

	public static GyroPIDController gyroPID;

	Command autoCommand;
	SendableChooser<String> mpChooser = new SendableChooser<>();
	/**For choosing what are starting configuration is*/
	SendableChooser<Side> wallChooser = new SendableChooser<>();
	/**For choosing what autonomous mode to run*/
	SendableChooser<String> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		intake = new Intake();
		climber = new Climber();

		gyroPID = new GyroPIDController();
		oi = new OI();

		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();

		wallChooser.addObject("Left Wall", Side.LEFT);
		wallChooser.addObject("Right Wall", Side.RIGHT);
		wallChooser.addObject("Middle Wall", Side.MIDDLE);

		autoChooser.addObject("Place Scale", "Scale");
		autoChooser.addObject("Place Switch", "Switch");
		autoChooser.addObject("Either, priority Scale", "EitherScale");
		autoChooser.addObject("Either, priority Switch", "EitherSwitch");
		autoChooser.addObject("Cross Line", "CrossAuto");

//		mpChooser.addObject("Cross Auto Line", "CrossAuto");
//		mpChooser.addObject("Left Wall : Scale", "LeftWallScale");
//		mpChooser.addObject("Left Wall : Switch", "LeftWallSwitch");
//		mpChooser.addObject("Right Wall : Scale", "RightWallScale");
//		mpChooser.addObject("Right Wall : Switch", "RightWallSwitch");
//		mpChooser.addObject("Middle Wall : Switch", "MiddleWallSwitch");

		SmartDashboard.putData("Starting Location", wallChooser);
		SmartDashboard.putData("Autonomous Mode", autoChooser);
		SmartDashboard.putData(driveTrain.gyro);
		SmartDashboard.putData(gyroPID);
		System.out.println("~~~Robot initialization complete!~~~");
		System.out.println("Run Test to generate the motion profile for autonomous.");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autoEnc();
	}

	// For if we ever get motion profiling working
	private void autoMP() {
		String auto = mpChooser.getSelected();
		// Lets the robot know which platform for the switches and scale is ours.
		String fmsMessage = DriverStation.getInstance().getGameSpecificMessage();
		if (auto.contains("Scale")) {
			auto += fmsMessage.charAt(1);
		} else if (auto.contains("Switch")) {
			auto += fmsMessage.charAt(0);
		} else {
			autoCommand = new CrossAuto();
			autoCommand.start();
			return;
		}
		autoCommand = new RunMotionProfile(auto);
		autoCommand.start();
	}

	// Using encoders and gyro to get around
	private void autoEnc() {
		autoCommand = null;
		String auto = autoChooser.getSelected();
		Side wall = wallChooser.getSelected();
		System.out.println("AUTO: User selected auto:" + auto + ", wall: " + wall);
		//The message sent from the Field Management System that determines where the switch and scale are
		String fmsMessage = DriverStation.getInstance().getGameSpecificMessage().substring(0, 2);
		System.out.println("AUTO: FMS Message: " + fmsMessage);
		Side swtch = Side.fromChar(fmsMessage.charAt(0));
		Side scale = Side.fromChar(fmsMessage.charAt(1));
		System.out.println("AUTO: Switch: " + swtch.toString() + ". Scale: " + scale.toString());
		if(auto.equals("CrossAuto")) {
			System.out.println("AUTO: Running CrossAuto");
			autoCommand = new CrossAuto();
		}
		else if(wall==Side.MIDDLE) {
			//The middle starting configuration has one auto function.
			System.out.println("AUTO: Running PlaceEitherSwitch");
			autoCommand = new PlaceEitherSwitch(swtch);
		}
		else if (auto.equals("Scale")) {
			System.out.println("AUTO: Running PlaceScale");
			autoCommand = new PlaceScale(scale, wall);
		} else if (auto.equals("Switch")) {
			// If the switch is on the opposite side, the robot will just cross auto
			System.out.println("AUTO: Running PlaceSwitch");
			autoCommand = new PlaceSwitch(swtch, wall);
		} else if (auto.contains("Either")) {
			System.out.println("AUTO: Running Either");
				if (auto.contains("Scale")) {
					if (scale == wall) {
						autoCommand = new PlaceScale(scale, wall);
					} else if (swtch == wall) {
						autoCommand = new PlaceSwitch(swtch, wall);
					}
				} else {
					if (swtch == wall) {
						autoCommand = new PlaceSwitch(swtch, wall);
					} else if (scale == wall) {
						autoCommand = new PlaceScale(scale, wall);
					}
				}
		}

		if (autoCommand == null) {
			System.out.println("AUTO: Running CrossAuto (by null case)");
			autoCommand = new CrossAuto();
		}
		autoCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Right Speed", driveTrain.getRightVel());
		SmartDashboard.putNumber("Left Speed", driveTrain.getLeftVel());
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		MotionProfileManager.generate("LeftWallScaleL", new Waypoint(0, 0, 0), new Waypoint(208, 0, 0),
				new Waypoint(266.148, -17, 0));
		MotionProfileManager.generate("LeftWallSwitchL", new Waypoint(0, 0, 0), new Waypoint(107.18, -42, 0));
		MotionProfileManager.generate("LeftWallScaleR", new Waypoint(0, 0, 0), new Waypoint(150, 0, 0),
				new Waypoint(208, -96.31, Math.toRadians(-90)), new Waypoint(266.148, -177.31, 0));
		MotionProfileManager.generate("RightWallScaleR", new Waypoint(0, 0, 0), new Waypoint(208, 0, 0),
				new Waypoint(266.148, -17, 0));
		MotionProfileManager.generate("RightWallScaleL", new Waypoint(0, 0, 0), new Waypoint(208, 0, 0),
				new Waypoint(0, -96.31, Math.toRadians(-90)), new Waypoint(58.148, 81, 90));
		MotionProfileManager.generate("RightWallSwitchR", new Waypoint(0, 0, 0), new Waypoint(107.18, 42, 0));
		MotionProfileManager.generate("MiddleWallSwitchR", new Waypoint(0, 0, 0), new Waypoint(108.55, 0, 0));
		MotionProfileManager.generate("Test", new Waypoint(0, 0, 0), new Waypoint(12, 0, 0)); // A test profile for
																								// tuning PID loops
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
