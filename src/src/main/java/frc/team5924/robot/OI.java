package frc.team5924.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    public Joystick controller = new Joystick(1);

    public OI()
    {}

    public double getXboxXAxis() 
    {
		  return controller.getRawAxis(0);
    }
    
    public double getXboxYAxis() 
    {
		  return controller.getRawAxis(1);
    }
    
    public double getXboxZAxis() 
    {
		  return controller.getRawAxis(2);
    }
    
    public double getXboxXRotate() 
    {
		  return controller.getRawAxis(3);
    }
    
    public double getXboxYRotate() 
    {
		  return controller.getRawAxis(4);
    }

    public double getXboxZRotate() 
    {
		  return controller.getRawAxis(5);
    }
    
    public boolean getXboxButton1() 
    {
		  return controller.getRawButton(1);
    }
    
    public boolean getXboxButton2() 
    {
		  return controller.getRawButton(2);
    }
    
    public boolean getXboxButton3() 
    {
      return controller.getRawButton(3);
    }
    
    public boolean getXboxButton4() 
    {
      return controller.getRawButton(4);
    }
    
    public boolean getXboxButton5() 
    {
      return controller.getRawButton(5);
    }
    
    public boolean getXboxButton6() 
    {
      return controller.getRawButton(6);
    }
    
    public boolean getXboxButton7() 
    {
      return controller.getRawButton(7);
    }
    
    public boolean getXboxButton8() 
    {
      return controller.getRawButton(8);
    }
    
    public boolean getXboxButton9() 
    {
      return controller.getRawButton(9);
    }

    public int getXboxPOV()
    {
      return controller.getPOV();
    }
}