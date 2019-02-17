package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Robot;

public final class MotorControl
{

	TalonSRX talon1, talon2, talon3, talon4, talon5, talon6;
	DriveMode driveMode;

	public static enum DriveMode
	{
		ONE, TWO, FOUR, SIX;
  	}
  

	public MotorControl(TalonSRX master)
	{
		driveMode = DriveMode.ONE;

		talon1 = master;
	}
  //This constructor is for 2 motors without follower or with follower

	public MotorControl(TalonSRX left, TalonSRX right)
	{
		driveMode = DriveMode.TWO;

		talon1 = right;
		talon2 = left;
 	}
  
  //This constructor is for 4 motors without follower

	public MotorControl(TalonSRX leftFront, TalonSRX leftBack, TalonSRX rightFront, TalonSRX rightBack)
	{
		driveMode = DriveMode.FOUR;
		talon1 = rightFront;
		talon2 = rightBack;
		talon3 = leftFront;
		talon4 = leftBack;
	}

  //This constructor is for 6 motors without follower

	public MotorControl(TalonSRX leftFront, TalonSRX leftMiddle, TalonSRX leftBack, TalonSRX rightFront, TalonSRX rightMiddle, TalonSRX rightBack)
	{
		driveMode = DriveMode.SIX;
		talon1 = rightFront;
		talon2 = rightMiddle;
		talon3 = rightBack;
		talon4 = leftFront;
		talon5 = leftMiddle;
		talon6 = leftBack;
  	}

	public void singleDrive(double moveValue)
	{
		setMotorOutputs(limitValue(moveValue), limitValue(moveValue));
	}

	public void buttonDrive(boolean moveValue)
	{
		if(Robot.e_oi.getXboxButton2())
		{
			//needs work
		}
	}

	public void switchDrive(boolean moveValue)
	{
		if(moveValue)
		{
			//setMotorOutputs
		}
	}

	public void driveTank(double leftMoveValue, double rightMoveValue)
	{
		leftMoveValue = limitValue(leftMoveValue);
		rightMoveValue = limitValue(rightMoveValue);
		setMotorOutputs(leftMoveValue, rightMoveValue);
	}
	
	public void driveArcade(double moveValue, double rotateValue)
	{
		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limitValue(moveValue);
		rotateValue = limitValue(rotateValue);
		
		if (moveValue >= 0.0)
		{
			if (rotateValue >= 0.0)
			{
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			}
			else
			{
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		}
		else
		{
			if (rotateValue >= 0.0)
			{
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
			else
			{
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		setMotorOutputs(rightMotorSpeed, leftMotorSpeed);
  	}
  

  //This method will write the motor percent output to both the right and the left
	public void setMotorOutputs(double left, double right)
	{
		if(driveMode == DriveMode.ONE)
		{
			talon1.set(ControlMode.PercentOutput, right);
		}
		if (driveMode == DriveMode.TWO)
		{
			talon1.set(ControlMode.PercentOutput, right);
			talon2.set(ControlMode.PercentOutput, left);
		}
		if (driveMode == DriveMode.FOUR)
		{
			talon1.set(ControlMode.PercentOutput, right);
			talon2.set(ControlMode.PercentOutput, right);
			talon3.set(ControlMode.PercentOutput, left);
			talon4.set(ControlMode.PercentOutput, left);
		}
		if (driveMode == DriveMode.SIX)
		{
			talon1.set(ControlMode.PercentOutput, right);
			talon2.set(ControlMode.PercentOutput, right);
			talon3.set(ControlMode.PercentOutput, right);
			talon4.set(ControlMode.PercentOutput, left);
			talon5.set(ControlMode.PercentOutput, left);
			talon6.set(ControlMode.PercentOutput, left);
		}
	}

  //This method is to limit the stick imputs to make sure that they dont go above or below 1 or -1
	private double limitValue(double value)
	{
		if (value > 1.0)
			value = 1.0;
		if (value < -1.0)
			value = -1.0;
		return value;
	}
}