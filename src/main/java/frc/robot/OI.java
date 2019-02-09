package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    public static Joystick driveControl = new Joystick(0);
    public static Joystick buttonControl = new Joystick(1);

    public OI()
    {}

    public double getXboxXAxis() {
		
		return driveControl.getRawAxis(0);
	}
    public double getXboxYAxis() {
		
		return driveControl.getRawAxis(1);
    }
    public double getXboxZAxis() {
		
		return driveControl.getRawAxis(2);
    }
    public double getXboxXRotate() {
		
		return driveControl.getRawAxis(3);
    }
    public double getXboxYRotate() {
		
		return driveControl.getRawAxis(4);
    }
    public double getXboxZRotate() {
		
		return driveControl.getRawAxis(5);
    }
}