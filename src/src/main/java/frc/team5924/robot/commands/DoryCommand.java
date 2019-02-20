package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5924.robot.Robot;

public class DoryCommand extends Command {
  public DoryCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.dory);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.dory.turnDegrees(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.dory.turnDegrees(Robot.e_oi.getXboxXAxis());
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
    Robot.dory.turnDegrees(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}