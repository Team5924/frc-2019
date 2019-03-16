/*
package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5924.robot.Robot;

public class HatchGrabberCommand extends Command {
  
  double time;
  public HatchGrabberCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.hatchGrabber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    time = System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if (!Robot.hatchGrabber.isSwitchSet()) {
      if(Robot.e_oi.getXboxButton1())
      {
        if(Robot.hatchGrabber.getSolenoid())
        {
            Robot.hatchGrabber.pneumaticDrive(false);
        }
        else
        {
            Robot.hatchGrabber.pneumaticDrive(true);
        }
      }
    }
    else {
      Robot.hatchGrabber.pneumaticDrive(false);
      Robot.hatchGrabber.resetCounter();
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
    Robot.hatchGrabber.pneumaticDrive(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}*/