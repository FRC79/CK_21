package org.usfirst.frc.team79.robot.pid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.usfirst.frc.team79.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;

public class GyroPIDController extends PIDController {
	
	private File propFile;
	private Properties pidProp;
	
	public GyroPIDController() {
		super(0, 0, 0, Robot.driveTrain.gyro, new GyroPIDOutput());
		File propDir = new File("/home/lvuser/pid/");
		if(!propDir.exists()) {
			propDir.mkdirs();
		}
		propFile = new File("/home/lvuser/pid/gyro.properties");
		if(!propFile.exists()) {
			try {
				propFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		pidProp = new Properties();
		try {
			pidProp.load(new FileInputStream(propFile));
			super.setPID(Double.parseDouble(pidProp.getProperty("P", "0")), Double.parseDouble(pidProp.getProperty("I", "0")), Double.parseDouble(pidProp.getProperty("D", "0")));
		} catch (FileNotFoundException e) {
			System.out.println("PID file does not exist");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Read a non-number for PID. Setting all PID to 0.");
		}
		this.setName("Gyro PID");
		this.setAbsoluteTolerance(0.2);
	}
	
	private void save() {
		try {
			System.out.println("Saving PID data"); //TODO remove when verified to work
			pidProp.store(new FileOutputStream(propFile), "This holds the PID values for rotation using the gyro.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setPID(double p, double i, double d) {
		super.setPID(p, i, d);
		save();
	}
	
	@Override
	public void setP(double p) {
		super.setP(p);
		save();
	}
	
	@Override
	public void setI(double i) {
		super.setI(i);
		save();
	}
	
	@Override
	public void setD(double d) {
		super.setD(d);
		save();
	}
	
}
