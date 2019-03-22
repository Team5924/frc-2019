package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5924.robot.Robot;

/*
 * This class allows auto-targeting in auto mode.
*/
public class AutoTargetCommand extends Command {

  public AutoTargetCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.limeLight);
    requires(Robot.driveTrain);
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
    //Robot.limeLight.Update_Limelight_Tracking();

    //if (Robot.limeLight.hasValidTarget())
    {
        //Robot.driveTrain.driveArcade(Robot.limeLight.getDriveCommand(),Robot.limeLight.getSteerCommand());
    }
    //else
    {
        Robot.driveTrain.driveArcade(0.0,0.0);
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
    Robot.driveTrain.driveArcade(0.0,0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}