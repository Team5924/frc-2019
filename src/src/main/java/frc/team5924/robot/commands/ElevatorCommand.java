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

    //Robot.elevator.resetCounter();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    
    //if(!Robot.elevator.isSwitchSet()){ 
      // move elevator only when switch is off   
        
      
      switch(Robot.e_oi.getXboxPOV())
      {
        case 180: Robot.elevator.buttonDrive(0); break;
        case 135: Robot.elevator.buttonDrive(1); break;
        case 90: Robot.elevator.buttonDrive(2); break;
        case 45: Robot.elevator.buttonDrive(3); break;
        case 0: Robot.elevator.buttonDrive(4); 
      }
   /*else {
      // Stop elevator is switch is on
      Robot.elevator.buttonDrive(0);
      //Robot.elevator.resetCounter();           
    }*/
    Robot.elevator.driveTank(Robot.e_oi.getXboxYAxis(),Robot.e_oi.getXboxYAxis());
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
    end();
  }
}