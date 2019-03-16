
package frc.team5924.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
//import frc.team5924.robot.commands.HatchGrabberCommand;

/**
 * Add your docs here.
 */
/*
public class HatchGrabber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

    Solenoid solenoid;
    DigitalInput hatchSwitch;
    Counter hatchCounter;

    hatchSwitch = new DigitalInput(Constants.HATCHGRABBER_SWITCH_CHANNEL);
    hatchCounter = new Counter(hatchSwitch);
    resetCounter();

    public HatchGrabber()
    {
        solenoid = new Solenoid(0);

        hatchSwitch = new DigitalInput(Constant.HATCHGRABBER_SWITCH_CHANNEL);
        hatchCounter = new Counter(hatchSwitch);
        resetCounter();
    }

    public void pneumaticDrive(boolean bool)
    {
        solenoid.set(bool);
    }

    public boolean getSolenoid() 
    {
        return solenoid.get();
    }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new HatchGrabberCommand());
  }

  public void resetCounter() {
    hatchCounter.reset();
  }
  public boolean isSwitchSet() {
    // return true if either hatch switch is on
    return hatchCounter.get() > 0;
  }
}*/