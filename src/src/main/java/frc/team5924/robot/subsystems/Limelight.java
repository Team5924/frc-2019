package frc.team5924.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import javax.lang.model.util.ElementScanner6;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.team5924.robot.commands.LimeLightTargetCommand;


public class Limelight extends Subsystem{

    // These numbers must be tuned for your Robot!  Be careful!
    // Ways you can find STEER_K and DRIVE_K,
    // 1, move the robot so that tx = 0, camera is pointing at the center of the tapes, 
    final double STEER_K = 0.03;                    // how hard to turn toward the target
    final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast
    final int PIPELINE_NUMBER = 0;                  // Need to double check the pipeline number
    final double XOFFSET = -24.50;                  // the horizontal offset in degrees, the camera is 9" from the center 
                                                     // During the auto and manual mode, stop the robot 3' away from cargo ship hatch
                                                     // then the tx = -24.50
    final double YOFFSET = 21.87;                    // vertical ty = 21.87, target center is 8" above camera height

    private boolean m_LimelightHasValidTarget = false;
    private double m_LimelightDriveCommand = 0.0;
    private double m_LimelightSteerCommand = 0.0;

    public enum Direction 
    {
        UP, DOWN, LEFT, RIGHT, ON_TARGET;
    }
    //creates a table for the pipeline
    NetworkTable table; 
    double x = 0; 
    double y = 0;
    double area = 0;

    /**
     * tx is the horizonta offset from crosshair to target
     * ty is the vertical offset from crosshair to target
     * 
     */
    NetworkTableEntry ts = table.getEntry("ts"); // angle of skew/rotation
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");

    public Limelight(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        table.getEntry("pipeline").setNumber(PIPELINE_NUMBER); 
    }

    public boolean hasValidTarget() {
        return m_LimelightHasValidTarget;
    }  

    public double getDriveCommand() {
        return m_LimelightDriveCommand;
    }       
 
    public double getSteerCommand() {
        return m_LimelightSteerCommand;
    }  

    public void printToDashboard() {
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        /**
         * Prints everything to the dashboard
         */
    }

    
    public double getAngle() {
        return this.ts.getDouble(0.0);
    }

    /**
     * gets the offset of the crosshair from the x axis
     * of the target
     */
    public double getXOffset() {
        return Math.abs(this.tx.getDouble(0.0));
    }

    /**
     * goes from the origin to the target value
     * @return
     */
    public Direction getXOffsetDirection() {
        // right pos, left neg
        if(this.tx.getDouble(0.0) > 0)
        {
            return Direction.RIGHT;
        }
        else if(this.tx.getDouble(0.0) < 0)
        {
            return Direction.LEFT;
        }
        else {
            return Direction.ON_TARGET;
        }
    }

    /**
     * gets the offset of the crosshair from the y axis
     * of the target
     * @return
     */
    public double getYOffset() {
        return Math.abs(this.ty.getDouble(0.0));

    }

    public Direction getYOffsetDirection() {
        // right pos, left neg
        if(this.ty.getDouble(0.0) > 0)
        {
            return Direction.UP;
        }
        else if(this.ty.getDouble(0.0) < 0)
        {
            return Direction.DOWN;
        }
        else {
            return Direction.ON_TARGET;
        }
    }

    /**
     * This function implements a simple method of generating driving and steering commands
     * based on the tracking data from a limelight camera.
     */
    public void Update_Limelight_Tracking()
    {
            double tv = table.getEntry("tv").getDouble(0);
            double tx = table.getEntry("tx").getDouble(0) + XOFFSET;
            double ty = table.getEntry("ty").getDouble(0) + YOFFSET;
            double ta = table.getEntry("ta").getDouble(0);

            if (tv < 1.0)
            {
                m_LimelightHasValidTarget = false;
                m_LimelightDriveCommand = 0.0;
                m_LimelightSteerCommand = 0.0;
                return;
            }

            m_LimelightHasValidTarget = true;

            // Start with proportional steering
            double steer_cmd = tx * STEER_K;
            m_LimelightSteerCommand = steer_cmd;

            // try to drive forward until the target area reaches our desired area
            double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

            // don't let the robot drive too fast into the goal
            if (drive_cmd > MAX_DRIVE)
            {
                drive_cmd = MAX_DRIVE;
            }
            m_LimelightDriveCommand = drive_cmd;
    }

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      setDefaultCommand(new LimeLightTargetCommand());
    }

}

