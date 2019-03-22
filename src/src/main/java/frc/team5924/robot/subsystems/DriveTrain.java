package frc.team5924.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.Counter;
//import edu.wpi.first.wpilibj.DigitalInput;
import frc.team5924.robot.Constants;
import frc.team5924.robot.commands.DriveCommand;
import frc.team5924.robot.subsystems.MotorControl;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX rrt,rrbr,rrbl,rlt,rlbr,rlbl;
  MotorControl motorControl;
  //DigitalInput leftSwitch, rightSwitch;
  // Counters are used to count the number of times the switches are clicked, this is to void
  // switching on/off too fast
  //Counter leftCounter, rightCounter;    

  public DriveTrain()
  {
    rrt = new TalonSRX(Constants.d_rearRightTop);
    rrbr = new TalonSRX(Constants.d_rearRightBottomRight);
    rrbl = new TalonSRX(Constants.d_rearRightBottomLeft);
    rlt = new TalonSRX(Constants.d_rearLeftTop);
    rlbr = new TalonSRX(Constants.d_rearLeftBottomRight);
    rlbl = new TalonSRX(Constants.d_rearLeftBottomLeft);

    // switches to stop the drivetrain 
    //leftSwitch = new DigitalInput(Constants.DRIVETRAIN_LEFT_SWITCH_CHANNEL);
    //rightSwitch = new DigitalInput(Constants.DRIVETRAIN_RIGHT_SWITCH_CHANNEL);
    //leftCounter = new Counter(leftSwitch);
    //rightCounter = new Counter(rightSwitch);
    //resetCounter();

    //Tells the left side that it should be inverted so that we drive stight with each side having positive motor values.
    rlt.setInverted(true);
    rlbr.setInverted(true);
    rlbl.setInverted(true);


    rlbr.set(ControlMode.Follower, rlt.getDeviceID());
    rlbr.set(ControlMode.Follower, rlt.getDeviceID());
    rrbr.set(ControlMode.Follower, rrt.getDeviceID());
    rrbl.set(ControlMode.Follower, rrt.getDeviceID());

    //Config all talons.
    
    configTalons(rrt);
    configTalons(rrbr);
    configTalons(rrbl);
    configTalons(rlt);
    configTalons(rlbr);
    configTalons(rlbl);

    motorControl = new MotorControl(rlt,rrt);
    rlt.setNeutralMode(NeutralMode.Brake);
  }


  public void configTalons(TalonSRX tSrx)
  {
    //Tells the talon that the max output that it can give is between 1 and -1 which would mean full forward and full backward.
    tSrx.configPeakOutputForward(1,0);
    tSrx.configPeakOutputReverse(-1,0);

    //Tells the talon that it should current limit its self so that we dont blow a 40Amp breaker.
    tSrx.configPeakCurrentLimit(40, 0);
    tSrx.enableCurrentLimit(true);
    tSrx.configContinuousCurrentLimit(40, 0);
    //The max output current is 40Amps for .25 of a second.
    tSrx.configPeakCurrentDuration(250, 0);

    //Tells the talon that it should only apply 12 volts (or less) to the motor.
    tSrx.configVoltageCompSaturation(12, 0);
  }


  public void driveArcade(double moveValue, double rotateValue)
  {
    motorControl.driveArcade(moveValue, rotateValue);
  }


  public void driveTank(double moveValue, double rotateValue)
  {
    motorControl.driveTank(moveValue, rotateValue);
  }

  public void setMotorOutputs(double left, double right)
  {
    motorControl.setMotorOutputs(left, right);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveCommand());
  }

  //public void resetCounter() {
    //leftCounter.reset();
    //rightCounter.reset();
  //}
  //public boolean isSwitchSet() {
    // return true if either left or right switches are on
   // return leftCounter.get() > 0 || rightCounter.get() > 0;
  //}
}