package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    public static void main(String[] args) {

        //creates a table for the pipeline
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double x = 0; double y = 0;
        double area = 0;
        NetworkTableEntry tx, ty;

        //turns the robot
        while(x != 0 && y != 0) {
            tx = table.getEntry("tx");
            ty = table.getEntry("ty");
            
            //reads value periodically
            x = tx.getDouble(0.0);
            y = ty.getDouble(0.0);

            //if x is positive on the crosshair
            if(x > 0) {
                /**
                 * code for the robot to turn to the right
                 */
            }
            else {
                //turns robot to the left
            }

            if (y > 0) {
                // turn camera up
            }
            else {
                //turn camera down
            }

            //do nothing is y == 0

            //sleep 0.1 second for the table to be updated
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        
        NetworkTableEntry ta;
        //move the robot closer once the target is centered
        while(area < 50) {
            ta = table.getEntry("ta");

            //reads values periodically
            area = ta.getDouble(0.0);

            if(area < 50) {
                //turn robot forward
            }

            //sleep 0.1 second foe the table to be updated
             
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }
}

