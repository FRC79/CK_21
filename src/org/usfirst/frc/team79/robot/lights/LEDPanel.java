package org.usfirst.frc.team79.robot.lights;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SerialPort.WriteBufferMode;

/**
 * For communicating with the arduino controller that control the LEDs
 *
 */
public class LEDPanel {
	
	public SerialPort port;
	
	public LEDPanel() {
		port = new SerialPort(9600, Port.kOnboard);
		port.setWriteBufferMode(WriteBufferMode.kFlushWhenFull);
	}
	
	public void sendImage(BufferedImage img) {
		if(img.getWidth()==20 && img.getHeight()==7) {
			byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
			byte[] buf = new byte[pixels.length*3];
			for(int i=0; i<pixels.length; i+=3) {
	            buf[i] = (byte) ((int) pixels[i] & 0xff); // blue
	            buf[i+1] = (byte) (((int) pixels[i + 1] & 0xff) << 8); // green
	            buf[i+2] = (byte) (((int) pixels[i + 2] & 0xff) << 16); // red
			}
			sendData(DataType.IMAGE, buf);
		}else System.out.println("You must use a 20x7 image");
	}
	
	public void sendText(String text) {
		sendData(DataType.TEXT, text.toUpperCase().getBytes());
	}
	
	public void sendAlliance() {
		byte[] buf = new byte[1];
		if(DriverStation.getInstance().getAlliance() == Alliance.Red) buf[0] = 1;
		else buf[0] = 0;
		sendData(DataType.ALLIANCE, buf);
	}
	
	public void sendData(DataType type, byte[] data) {
		ByteBuffer bbuf = ByteBuffer.allocate(4);
		bbuf.putInt(data.length);
		bbuf.flip();
		port.write(bbuf.array(), 4);
		byte[] buf = new byte[data.length+1];
		buf[0] = type.id;
		for(int i=0; i<data.length; i++) {
			buf[i+1] = data[i];
		}
		port.write(buf, buf.length);
		port.flush();
	}
	
}
