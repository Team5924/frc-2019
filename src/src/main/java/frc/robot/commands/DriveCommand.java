package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveCommand extends Command {
  
  double time;
  public DriveCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
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
    //Robot.driveTrain.driveArcade(Robot.m_oi.driveControl.getY(Hand.kLeft),Robot.m_oi.driveControl.getX(Hand.kRight));
    
    //Robot.driveTrain.driveTank(Robot.m_oi.getXboxYAxis()*0.2,Robot.m_oi.getXboxZRotate()*0.3);
    double time2 = System.currentTimeMillis();
    System.out.println(time2-time);
    if(time2-time<2000)
    {
      System.out.println("driving");
      Robot.driveTrain.driveTank(-0.3,-0.4);
    }
    else 
    Robot.driveTrain.driveTank(0,0);
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