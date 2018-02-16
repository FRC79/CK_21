/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.commands.ClimberDown;
import org.usfirst.frc.team79.robot.commands.ClimberUp;
import org.usfirst.frc.team79.robot.commands.IntakeIn;
import org.usfirst.frc.team79.robot.commands.IntakeOut;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick drive = new Joystick(0);
	public Button intakeIn = new JoystickButton(drive,7); 
	public Button intakeOut = new JoystickButton(drive,8);

	public Joystick operator = new Joystick(1);
	
	public OI() {
		intakeIn.whileHeld(new IntakeIn());
		intakeOut.whileHeld(new IntakeOut());
	}
	
}
