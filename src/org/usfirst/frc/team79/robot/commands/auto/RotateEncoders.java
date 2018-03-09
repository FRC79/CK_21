package org.usfirst.frc.team79.robot.commands.auto;

import org.usfirst.frc.team79.robot.Robot;
import org.usfirst.frc.team79.robot.RobotDimensions;

import edu.wpi.first.wpilibj.command.Command;

public class RotateEncoders extends Command{
	
	public double angularDisplacement;
	
	public double leftInitial, rightInitial, leftFinal, rightFinal;
	
	public RotateEncoders(double degrees) {
		this.angularDisplacement = Math.toRadians(degrees);
	}

	@Override
	protected void initialize() {
		leftInitial = Robot.driveTrain.getLeftInches();
		rightInitial = Robot.driveTrain.getRightInches();
		leftFinal = leftInitial + angularDisplacement*RobotDimensions.ROBOT_WIDTH/2;
		rightFinal = rightInitial - angularDisplacement*RobotDimensions.ROBOT_WIDTH/2;
	}
	
	@Override
	protected void execute() {
		double sign = angularDisplacement < 0 ? -1 : angularDisplacement > 0 ? 1 : 0;
		Robot.driveTrain.arcadeDrive(0, sign*0.25);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.getLeftInches() < leftFinal && Robot.driveTrain.getRightInches() < rightFinal;
	}

}