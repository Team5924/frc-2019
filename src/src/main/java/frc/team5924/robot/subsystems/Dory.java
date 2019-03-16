package frc.team5924.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5924.robot.Constants;
import frc.team5924.robot.commands.DoryCommand;
import frc.team5924.robot.subsystems.MotorControl;

/**
 * Add your docs here.
 */
public class Dory extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX mMaster;
  MotorControl motorControl;




  public Dory()
  {
    mMaster = new TalonSRX(Constants.HOGId);
 
    //Config all talons.
    configTalons(mMaster);

    motorControl = new MotorControl(mMaster);
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


  public void turnDegrees(double moveValue)
  {
    motorControl.turnDegrees(moveValue);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DoryCommand());
  }
}