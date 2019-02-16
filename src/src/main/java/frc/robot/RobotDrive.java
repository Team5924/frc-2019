package frc.robot;

import frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

public class RobotDrive extends Subsystem
{
    public static Talon rearRightTop = new Talon(1);
    public static Talon rearRightBottomRight = new Talon(2);
    public static Talon rearRightBottomLeft = new Talon(7);
    public static Talon rearLeftTop = new Talon(14);
    public static Talon rearLeftBottomRight = new Talon(13);
    public static Talon rearLeftBottomLeft = new Talon(15);

    public static SpeedControllerGroup rightSide = new 
        SpeedControllerGroup(rearRightTop, rearRightBottomLeft, 
        rearRightBottomRight);

    public static SpeedControllerGroup leftSide = new 
    SpeedControllerGroup(rearLeftTop, rearLeftBottomLeft, 
    rearLeftBottomRight);

    public static DifferentialDrive rDrive = new DifferentialDrive(leftSide, rightSide);

	public Encoder leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
	public Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
    
    private String turnStatus = "";
	private double angleError;
	private double angle;
	
	public RobotDrive(){
		//pulses per revolution: 1440
		//E4T MINI optical encoder
		//rightEncoder.setDistancePerPulse(distancePerPulse);
		//leftEncoder.setDistancePerPulse(distancePerPulse);	
		
	}
	//PRINT DRIVE INFO tO SMARTDASHBOARD
	public void printMotorInfo(){
		/*
		SmartDashboard.putNumber("Rear Right Top Motor Voltage", rearRightTop.getMotorOutputVoltage());
		SmartDashboard.putNumber("Rear Right Bottom Left Motor Voltage", rearRightBottomLeft.getMotorOutputVoltage());
		SmartDashboard.putNumber("Rear Right Bottom Right Motor Voltage", rearRightBottomRight.getMotorOutputVoltage());
		SmartDashboard.putNumber("Rear Left Top Motor Voltage", rearLeftTop.getMotorOutputVoltage());
		SmartDashboard.putNumber("Rear Left Top Motor Voltage", rearLeftTop.getMotorOutputVoltage());
		SmartDashboard.putNumber("Rear Left Top Motor Voltage", rearLeftTop.getMotorOutputVoltage());
		*/
	}
	//AUTO TESTING
	public void printAutoMotorInfo(){
		
		SmartDashboard.putNumber("Left Encoder Position", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Encoder Position", rightEncoder.getDistance());
		//SmartDashboard.putNumber("Z-Axis Angle", -Robot.imu.getAngleZ());
    	SmartDashboard.putString("Robot Turn Status", turnStatus);
	}
	//TELE STUFF
	public void driveRobotBase(){
		//angle = Robot.imu.getAngleZ();
		
		rDrive.tankDrive(-Robot.m_oi.getXboxYAxis(), Robot.m_oi.getXboxXAxis());
	}
	//AUTO STUFF
	public void moveRobot(double robotSpeedReq){
		rDrive.tankDrive(-robotSpeedReq, 0);
	}
	
	public void stopRobot() {
		rDrive.tankDrive(0, 0);
	}
	
	public void turnRobotDegrees(String turnTypeReq, double degreesReq) {
		//angle = Robot.imu.getAngleZ();
		angleError = degreesReq - Math.abs(angle);
		if(turnTypeReq == "R"){
	    	/**if(-Robot.imu.getAngleZ() < degreesReq){
	    		rDrive.arcadeDrive(0, angleError * errorFactor);
	    	}**/
			rDrive.tankDrive(0, 0.5);
	    } else if(turnTypeReq == "L"){
			/**if(-Robot.imu.getAngleZ() > -degreesReq){
				rDrive.arcadeDrive(0, -angleError * errorFactor);
			}**/
	    	rDrive.tankDrive(0, -0.5);
	    }
	}
	
	public double getAverageEncoderPosition() {
		
		//return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		return leftEncoder.getDistance();
	}
	
	public void resetEncoders() {
		
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	public boolean turnFinish(){
		if(Math.abs(angleError) < 1) {
			turnStatus = "Complete";
			return true;
		}
		
		return false;
	}
	
    public void initDefaultCommand() 
    {
		setDefaultCommand(new DriveCommand());
    }
}