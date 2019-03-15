package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5924.robot.Robot;

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
    Robot.gyro.reset();

    Robot.driveTrain.resetCounter();


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    //Robot.driveTrain.driveArcade(Robot.m_oi.getXboxYAxis(),-Robot.m_oi.getXboxZAxis());
    while(Robot.m_oi.getXboxButton7())
    {
      double angle = -Robot.gyro.getAngle();
      Robot.driveTrain.driveArcade(-0.5 , Math.min(0.5, Math.max(angle/100, -0.5)));
    }



    // move the robot only when switch is off
    if(!Robot.driveTrain.isSwitchSet()){
      Robot.driveTrain.driveArcade(Robot.m_oi.getXboxYAxis(),-Robot.m_oi.getXboxZAxis());
    } else {
      Robot.driveTrain.driveArcade(0, 0);
      Robot.driveTrain.resetCounter();      // I don't know if this is required but where else do you reset the counter once it's stop?
    }
        


    Robot.driveTrain.driveTank(Robot.m_oi.getXboxYAxis()*0.7,Robot.m_oi.getXboxZRotate()* 0.7);   /*double time2 = System.currentTimeMillis();
    /*System.out.println(time2-time);
    if(time2-time<2000)
    {
      System.out.println("driving");
      Robot.driveTrain.driveTank(-0.3,-0.4);
    }
    else */
    //Robot.driveTrain.driveTank(.7,.7);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return Robot.driveTrain.isSwitchSet();
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