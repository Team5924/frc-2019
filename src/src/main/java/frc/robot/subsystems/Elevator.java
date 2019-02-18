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
    private final TalonSRX mMaster,  mLeftSlave;
    MotorControl motorControl;

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
}