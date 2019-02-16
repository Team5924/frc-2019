package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorCommand extends Command {
  public ElevatorCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.elevator.buttonDrive(Robot.e_oi.getXboxButton8());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.elevator.buttonDrive(Robot.e_oi.getXboxButton2());
    Robot.elevator.buttonDrive(Robot.e_oi.getXboxButton3());
    Robot.elevator.buttonDrive(Robot.e_oi.getXboxButton4());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.elevator.buttonDrive(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}