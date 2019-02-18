package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristCommand extends Command {
  public WristCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.wrist.pneumaticDrive(false);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.wrist.pneumaticDrive(Robot.e_oi.getXboxButton2());
    Robot.wrist.pneumaticDrive(Robot.e_oi.getXboxButton3());
    Robot.wrist.pneumaticDrive(Robot.e_oi.getXboxButton4());
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
    Robot.wrist.pneumaticDrive(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}