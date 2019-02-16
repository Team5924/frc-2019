package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;



public class Limelight {
    
    public enum Direction 
    {
        UP, DOWN, LEFT, RIGHT, ON_TARGET;
    }
    //creates a table for the pipeline
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
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


    public static void main(String[] args) {}
        
    
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
}

