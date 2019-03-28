package frc.team5924.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5924.robot.Constants;
import frc.team5924.robot.commands.ClimberCommand;
import frc.team5924.robot.subsystems.MotorControl;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid backClimberSolenoid;
  DoubleSolenoid frontClimberSolenoid;

  public Climber()
  {
    backClimberSolenoid = new DoubleSolenoid(0, 1);
    frontClimberSolenoid = new DoubleSolenoid(2, 3);
  }

  public void execute()
  {
    frontClimberSolenoid.set(Value.kForward);
    backClimberSolenoid.set(Value.kForward);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimberCommand());
  }
}