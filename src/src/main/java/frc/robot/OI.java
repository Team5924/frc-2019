package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    public Joystick driveControl = new Joystick(1);
    public Joystick buttonControl = new Joystick(2);

    public OI()
    {}

    public double getXboxXAxis() 
    {
		  return driveControl.getRawAxis(0);
    }
    
    public double getXboxYAxis() 
    {
		  return driveControl.getRawAxis(1);
    }
    
    public double getXboxZAxis() 
    {
		  return driveControl.getRawAxis(2);
    }
    
    public double getXboxXRotate() 
    {
		  return driveControl.getRawAxis(3);
    }
    
    public double getXboxYRotate() 
    {
		  return driveControl.getRawAxis(4);
    }

    public double getXboxZRotate() 
    {
		  return driveControl.getRawAxis(5);
    }
    
    public boolean getXboxButton1() 
    {
		  return driveControl.getRawButton(1);
    }
    
    public boolean getXboxButton2() 
    {
		  return driveControl.getRawButton(2);
    }
    
    public boolean getXboxButton3() 
    {
      return driveControl.getRawButton(3);
    }
    
    public boolean getXboxButton4() 
    {
      return driveControl.getRawButton(4);
    }
    
    public boolean getXboxButton5() 
    {
      return driveControl.getRawButton(5);
    }
    
    public boolean getXboxButton6() 
    {
      return driveControl.getRawButton(6);
    }
    
    public boolean getXboxButton7() 
    {
      return driveControl.getRawButton(7);
    }
    
    public boolean getXboxButton8() 
    {
      return driveControl.getRawButton(8);
    }
    
    public boolean getXboxButton9() 
    {
      return driveControl.getRawButton(9);
    }
}