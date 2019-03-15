package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5924.robot.Robot;

public class ClimberCommand extends Command {
  public ClimberCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.m_oi.getXboxButton1()&&Robot.m_oi.getXboxButton5()&&Robot.startTime-Robot.endTime==120000)
    {
        Robot.climber.execute();
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}