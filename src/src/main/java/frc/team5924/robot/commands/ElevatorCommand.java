package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5924.robot.Robot;

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
    //try to always start at bottom
    Robot.elevator.buttonDrive(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.e_oi.getXboxButton2())
    {
      Robot.elevator.buttonDrive(0);
    }
    if(Robot.e_oi.getXboxButton3())
    {
      Robot.elevator.buttonDrive(2);
    }
    if(Robot.e_oi.getXboxButton4())
    {
      Robot.elevator.buttonDrive(4);
    }
    switch(Robot.e_oi.getXboxPOV())
    {
      case 4: Robot.elevator.buttonDrive(1); break;
      case 6: Robot.elevator.buttonDrive(3); break;
      case 0: Robot.elevator.buttonDrive(5);
    }
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
    Robot.elevator.buttonDrive(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}