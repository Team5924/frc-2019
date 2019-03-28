package frc.team5924.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team5924.robot.commands.HatchGrabberCommand;
import frc.team5924.robot.Constants;
/**
 * Add your docs here.
 */

public class HatchGrabber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

    DoubleSolenoid grabberSolenoid;
    DigitalInput hatchSwitch;
    Counter hatchCounter;
    //resetCounter();

    public HatchGrabber()
    {
        grabberSolenoid = new DoubleSolenoid(0, 1);

        //hatchSwitch = new DigitalInput(Constants.HATCHGRABBER_SWITCH_CHANNEL);
        //hatchCounter = new Counter(hatchSwitch);
        resetCounter();
    }

    public void pneumaticDrive(boolean bool)
    {
      if(bool) {
        grabberSolenoid.set(Value.kForward);
      } else {
        grabberSolenoid.set(Value.kReverse);
      }
        
    }

    public boolean getSolenoid() 
    {
      return grabberSolenoid.get() == Value.kForward;
    }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new HatchGrabberCommand());
  }

  public void resetCounter() {
    //hatchCounter.reset();
  }   
  public boolean isSwitchSet() {
    // return true if either hatch switch is on
    return hatchCounter.get() > 0;
  }
  
}
