package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.Robot;
import org.usfirst.frc.team79.robot.RobotDimensions;
import org.usfirst.frc.team79.robot.pathfinding.MotionProfileManager;
import org.usfirst.frc.team79.robot.util.Conversions;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

public class RunMotionProfile extends Command{

	public String autoName;
	public Thread right, left;
	
	private volatile boolean leftLoaded, rightLoaded;
	
	public RunMotionProfile(String autoName) {
		this.autoName = autoName;
	}
	
	@Override
	protected void initialize() {
		rightLoaded = leftLoaded = false;
		MotionProfileManager.load(autoName);
		Robot.driveTrain.frontRight.set(ControlMode.MotionProfile, SetValueMotionProfile.Disable.value);
		Robot.driveTrain.frontLeft.set(ControlMode.MotionProfile, SetValueMotionProfile.Disable.value);
		right = new Thread("Load Right MP") {
			@Override
			public void run() {
				loadPoint(MotionProfileManager.rightPath, Robot.driveTrain.frontRight);
				rightLoaded = true;
			}
		};
		left = new Thread("Load Left MP") {
			@Override
			public void run() {
				loadPoint(MotionProfileManager.leftPath, Robot.driveTrain.frontLeft);
				leftLoaded = true;
			}
		};
		right.run();
		left.run();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Robot.driveTrain.frontRight.set(ControlMode.MotionProfile, SetValueMotionProfile.Enable.value);
		Robot.driveTrain.frontLeft.set(ControlMode.MotionProfile, SetValueMotionProfile.Enable.value);
	}
	
	@Override
	protected void execute() {
		
	}
	
	private void loadPoint(Trajectory path, TalonSRX talon) {
		for(int i=0; i<path.segments.length; i++) {
			Segment seg = path.segments[i];
			TrajectoryPoint point = new TrajectoryPoint();
			point.position = Conversions.metersToFeet(seg.position)/RobotDimensions.WHEEL_CIRCUMFERENCE; //TODO: Currently in units of rotations. Need to convert to units of encoder
			point.velocity = (Conversions.metersToFeet(seg.velocity)/RobotDimensions.WHEEL_CIRCUMFERENCE)*60d; //TODO: Currently in RPM. Need to convert to encoder units/min
			point.headingDeg = Math.toDegrees(seg.heading);
			point.zeroPos = i==0;
			point.isLastPoint = i==path.segments.length-1;;
			talon.pushMotionProfileTrajectory(point);
		}
	}
	
	@Override
	protected void end() {
		Robot.driveTrain.stopMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.frontRight.getMotionProfileTopLevelBufferCount()==0 && Robot.driveTrain.frontLeft.getMotionProfileTopLevelBufferCount()==0 && leftLoaded && rightLoaded;
	}
	
	

}
