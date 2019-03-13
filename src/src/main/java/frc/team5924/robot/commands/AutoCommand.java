package frc.team5924.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5924.robot.Constants;

/**
 *
 */
public class AutoCommand extends CommandGroup {
	
    public AutoCommand(String pModeStr) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	// To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        
        switch(pModeStr){
            case "L": // Robot starts on the left
                // 3 paths to drive
                addSequential(new DrivePathCommand(Constants.LEFT_PATH1_LEFT_CSV, Constants.LEFT_PATH1_RIGHT_CSV));
                addSequential(new AutoTargetCommand());
                addSequential(new DrivePathCommand(Constants.LEFT_PATH2_LEFT_CSV, Constants.LEFT_PATH2_RIGHT_CSV));
                addSequential(new DrivePathCommand(Constants.LEFT_PATH3_LEFT_CSV, Constants.LEFT_PATH3_RIGHT_CSV));
                break;
            case "R": // Robot starts on the right
                addSequential(new DrivePathCommand(Constants.RIGHT_PATH1_LEFT_CSV, Constants.RIGHT_PATH1_RIGHT_CSV));
                addSequential(new AutoTargetCommand());                
                addSequential(new DrivePathCommand(Constants.RIGHT_PATH2_LEFT_CSV, Constants.RIGHT_PATH2_RIGHT_CSV));
                addSequential(new DrivePathCommand(Constants.RIGHT_PATH3_LEFT_CSV, Constants.RIGHT_PATH3_RIGHT_CSV));            
                break;
            default: // default to "T" test mode
                break;
        }    	
    } 	
}

    

