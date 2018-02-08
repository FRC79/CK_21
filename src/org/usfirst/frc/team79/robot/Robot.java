/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.subsystems.Climber;
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
	
	public static CameraServer camServer;

	Command autoCommand;
	SendableChooser<String> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		intake = new Intake();
		climber = new Climber();

		gyroPID = new GyroPIDController();
		oi = new OI();
		
		UsbCamera cam = new UsbCamera("cam0", 0);
		cam.setBrightness(70);
		camServer = CameraServer.getInstance();
		camServer.addCamera(cam);
		
		chooser.addObject("Left Wall : Scale", "LeftWallScale");
		
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData(driveTrain.gyro);
		SmartDashboard.putData(gyroPID);
		System.out.println("~~~Robot initialization complete!~~~");
		System.out.println("Run Test to generate the motion profile for autonomous.");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		String auto = chooser.getSelected();
		//Lets the robot know which platform for the switches and scale is ours.
		String fmsMessage = DriverStation.getInstance().getGameSpecificMessage();
		if(auto.contains("Scale")) {
			auto += fmsMessage.charAt(1);
		}else if(auto.contains("Switch")){
			auto += fmsMessage.charAt(0);
		}
		autoCommand = new RunMotionProfile(auto);
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
		MotionProfileManager.generate("LeftWallScaleL", new Waypoint(0,0,0), new Waypoint(208, 0, 0), new Waypoint(266.148, -17, 0));
		MotionProfileManager.generate("LeftWallSwitchL", new Waypoint(0,0,0), new Waypoint(107.18, -42, 0));
		MotionProfileManager.generate("LeftWallScaleR", new Waypoint(0, 0, 0), new Waypoint(150, 0, 0), new Waypoint(208, -96.31, Math.toRadians(-90)), new Waypoint(266.148, -177.31, 0));
		MotionProfileManager.generate("RightWallScaleR", new Waypoint(0, 0, 0), new Waypoint(208, 0, 0), new Waypoint(266.148, -17, 0));
		MotionProfileManager.generate("RightWallScaleL", new Waypoint(0, 0, 0), new Waypoint(208, 0, 0), new Waypoint(0, -96.31, Math.toRadians(-90)), new Waypoint(58.148, 81, 90));
		MotionProfileManager.generate("RightWallSwitchR", new Waypoint(0, 0, 0), new Waypoint(107.18, 42, 0));
		MotionProfileManager.generate("MiddleWallSwitchR", new Waypoint(0, 0, 0), new Waypoint(108.55, 0, 0));
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
