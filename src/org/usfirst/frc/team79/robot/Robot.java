/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team79.robot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.usfirst.frc.team79.robot.pid.GyroPIDController;
import org.usfirst.frc.team79.robot.subsystems.DriveTrain;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public static GyroPIDController gyroPID;
	
	public static CameraServer camServer;
	public static AxisCamera camera;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();

		File pidFile = new File("/home/lvuser/pid/gyro.properties");
		Properties pidProp = new Properties();
		try {
			pidProp.load(new FileInputStream(pidFile));
			gyroPID = new GyroPIDController(Double.parseDouble(pidProp.getProperty("P", "0")), Double.parseDouble(pidProp.getProperty("I", "0")), Double.parseDouble(pidProp.getProperty("D", "0")));
		} catch (FileNotFoundException e) {
			System.out.println("PID file does not exist");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Some idiot saved something that wasn't a number... Setting all PID to 0.");
			gyroPID = new GyroPIDController(0, 0, 0);
		}
		oi = new OI();
		
		camServer = CameraServer.getInstance();
		camera = camServer.addAxisCamera("10.0.79.3");
		
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData(driveTrain.gyro);
		SmartDashboard.putData(gyroPID);
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
		//Lets the robot know which platform for the switches and scale is ours.
		String fmsMessage = DriverStation.getInstance().getGameSpecificMessage();
		if(fmsMessage.charAt(0)=='L') {
			//Auto for first switch placement on left
		}else {
			//Auto for first switch placement on right
		}
		
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
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
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		System.out.println(driveTrain.gyro.getRate());
	}
}
