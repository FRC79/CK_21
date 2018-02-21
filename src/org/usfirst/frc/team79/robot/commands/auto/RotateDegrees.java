package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.Robot;
import org.usfirst.frc.team79.robot.pid.GyroPIDController;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateDegrees extends Command {
	
	//Hi Ashwin. It's Noah from the past.
	//So I heard you need help tuning the Gyro PID loop.
	//You've come to the right place.
	//First: Go down to line 69 and switch around the comments so it returns false.
	//On the Suffleboard, make sure you've set your Gyro field to a graph of some sort.
	//Now in the Gyro PID section, set a small P value (like 0.5 or something).
	//In OI, uncomment the test button.
	//Run the command using X on the operator controller
	//If the system over-oscillates, lower P
	//If it takes a lot of time for it to eventually slow down, increase P.
	//Note the value of P that causes it to oscillate steadily. Call it Ku.
	//Disable the bot and note the time period in whatever unit is on the graph of the period of the oscillation. Call it Tu.
	//Use the page of my notebook I posted on Discord to determine all the values of P, I, and D.
	//Now run the command again and see if it all works fine.
	//If not, panic.
	//Try it at 90 degrees
	
	//Good luck.
	
	//My code must
	//will be
	//perfect

	public GyroPIDController gyro;
	public double displacement;
	
	private int count;
	
	/**
	 * Rotates a specified numbers of degrees
	 * @param displacement in degrees
	 */
	public RotateDegrees(double displacement) {
		gyro = Robot.gyroPID;
		this.displacement = displacement;
	}
	
	@Override
	protected void initialize() {
		gyro.setSetpoint(Robot.driveTrain.gyro.getAngle()+this.displacement);
		gyro.enable();
	}
	
	@Override
	protected void end() {
		gyro.disable();
		Robot.driveTrain.frontLeft.set(ControlMode.PercentOutput, 0);
		Robot.driveTrain.frontRight.set(ControlMode.PercentOutput, 0);
		SmartDashboard.putNumber("Gyro", Robot.driveTrain.gyro.getAngle());
	}
	
	@Override
	protected boolean isFinished() {
		if(gyro.onTarget()) {
			count++;
		}else count = 0;
		return count >= 3 && gyro.isEnabled();
//		return false;
	}

}
