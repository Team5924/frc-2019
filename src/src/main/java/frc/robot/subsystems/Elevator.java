package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;
import frc.robot.commands.ElevatorCommand;
import frc.robot.subsystems.MotorControl;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Elevator extends Subsystem
{

    private static Elevator mInstance = null;
    private final TalonSRX mMaster, mRightSlave, mLeftSlaveA, mLeftSlaveB;
    MotorControl motorControl;

    public Elevator()
    {
        mMaster = new TalonSRX(Constants.kElevatorMasterId);
        mRightSlave = new TalonSRX(Constants.kElevatorMasterId);
        mLeftSlaveA = new TalonSRX(Constants.kElevatorMasterId);
        mLeftSlaveB = new TalonSRX(Constants.kElevatorMasterId);
        mMaster.enableCurrentLimit(true);

        mMaster.selectProfileSlot(0, 0);

        mMaster.overrideLimitSwitchesEnable(true);
        mMaster.overrideSoftLimitsEnable(false);

        mMaster.enableVoltageCompensation(true);

        mMaster.setInverted(true);
        mMaster.setSensorPhase(true);

        mRightSlave.set(ControlMode.Follower, mMaster.getDeviceID());
        mLeftSlaveA.set(ControlMode.Follower, mMaster.getDeviceID());
        mLeftSlaveB.set(ControlMode.Follower, mMaster.getDeviceID());
        mRightSlave.setInverted(true);
        mLeftSlaveA.setInverted(false);
        mLeftSlaveB.setInverted(false);

        motorControl = new MotorControl(mMaster);
    }
    
    public synchronized static Elevator getInstance() {
        if (mInstance == null) {
            mInstance = new Elevator();
        }
        return mInstance;
    }

    public void buttonDrive(boolean moveValue)
	{
		buttonDrive(moveValue);
	}

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      setDefaultCommand(new ElevatorCommand());
    }
}