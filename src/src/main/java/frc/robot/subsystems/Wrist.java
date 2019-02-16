package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
//import frc.robot.Constants;
import frc.robot.commands.WristCommand;

public class Wrist extends Subsystem
{
    
    //private boolean bool;
    private static Wrist mInstance = null;
    //private final TalonSRX mMaster;
    MotorControl motorControl;

    public Wrist()
    {
        //bool = false;
        //mMaster = new TalonSRX(Constants.w_wristId);
    }

    public synchronized static Wrist getInstance() {
        if (mInstance == null) {
            mInstance = new Wrist();
        }
        return mInstance;
    }

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      setDefaultCommand(new WristCommand());
    }
}