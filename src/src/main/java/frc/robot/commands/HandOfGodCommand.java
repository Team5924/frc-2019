package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HandOfGodCommand extends Command {
  
  double time;
  public HandOfGodCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.handOfGod);
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
    double time2 = System.currentTimeMillis();
    if(Robot.e_oi.getXboxButton7())
    {
        if(time2-time>2500)
        {
            Robot.handOfGod.outtakeHOG(false);
        }
        else
        {
            Robot.handOfGod.outtakeHOG(true);
        }
    }
    if(Robot.e_oi.getXboxButton8())
    {
        if(time2-time>2500)
        {
            Robot.handOfGod.intakeHOG(false);
        }
        else
        {
            Robot.handOfGod.intakeHOG(true);
        }
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
    Robot.driveTrain.driveArcade(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}