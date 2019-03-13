package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import frc.team5924.robot.Robot;
import frc.team5924.robot.subsystems.*;

/*
 * This class allows auto-targeting in teleop mode. So basically the robot can be
 * driven close to the target and then user presses A to make the robot auto-target 
 * the target.
*/
public class LimeLightTargetCommand extends Command {

  public LimeLightTargetCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.limeLight);
    requires(Robot.limeLight);
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
    Robot.limeLight.Update_Limelight_Tracking();

    double steer = Robot.m_oi.getXboxXAxis();
    double drive = -1*Robot.m_oi.getXboxYAxis(); // Note that the move value is reversed by x -1
    boolean auto = Robot.m_oi.getXboxButton1(); // Which one is button one? Need to test it out

    // Need to tune these numbers
    steer *= 0.70;
    drive *= 0.70;

    if (auto)
    {
      if (Robot.limeLight.hasValidTarget())
      {
            Robot.driveTrain.driveArcade(Robot.limeLight.getDriveCommand(),Robot.limeLight.getSteerCommand());
      }
      else
      {
        Robot.driveTrain.driveArcade(0.0,0.0);
      }
    }
    else
    {
      Robot.driveTrain.driveArcade(drive,steer);
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