package frc.team5924.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.team5924.robot.Constants;
import frc.team5924.robot.commands.ElevatorCommand;
import frc.team5924.robot.subsystems.MotorControl;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Elevator extends Subsystem
{

    private static Elevator mInstance = null;
    private final TalonSRX mMaster,  mLeftSlave;
    MotorControl motorControl;
    DigitalInput topSwitch, bottomSwitch;
    Counter topCounter, bottomCounter;

    public Elevator()
    {
        mMaster = new TalonSRX(Constants.kElevatorMasterId);
        mLeftSlave = new TalonSRX(Constants.kElevatorLeftSlaveId);

        mMaster.enableCurrentLimit(true);

        mMaster.overrideLimitSwitchesEnable(true);
        mMaster.overrideSoftLimitsEnable(false);

        mMaster.enableVoltageCompensation(true);

        mMaster.setInverted(true);
        mMaster.setSensorPhase(true);

        mLeftSlave.set(ControlMode.Follower, mMaster.getDeviceID());
        mLeftSlave.setInverted(false);

        motorControl = new MotorControl(mMaster);

        topSwitch = new DigitalInput(Constants.ELEVATOR_TOP_SWITCH_CHANNEL);
        bottomSwitch = new DigitalInput(Constants.ELEVATOR_BOTTOM_SWITCH_CHANNEL);
        topCounter = new Counter(topSwitch);
        bottomCounter = new Counter(bottomSwitch);
        resetCounter();
    }
    
    public synchronized static Elevator getInstance() {
        if (mInstance == null) {
            mInstance = new Elevator();
        }
        return mInstance;
    }
    
    public void buttonDrive(int moveValue)
    {
        motorControl.buttonDrive(moveValue);
    }

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      setDefaultCommand(new ElevatorCommand());
    }

    public void resetCounter() {
        topCounter.reset();
        bottomCounter.reset();
      }
      public boolean isSwitchSet() {
        // return true if either top or bottom switches are on
        return topCounter.get() > 0 || bottomCounter.get() > 0;
      }
}