package frc.team5924.robot.commands;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5924.robot.Robot;
import frc.team5924.robot.subsystems.DriveTrain;

import frc.team5924.robot.Constants;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.SpeedController;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class DrivePathCommand extends Command {

    private DriveTrain drivetrain;

    private static double MAX_VELOCITY = 3.553968; // in meter/s which equals to 11.66 ft/s 
    private static double kA = 0;
    private static double kP_TURN = 0.8; // in all examples this is 0.8, but some other teams use 0.05. ??
    private static final int TICKS_PER_REVOLUTION = 1024;
    private static final double WHEEL_DIAMETER = 6.0 / 12.0; // 6 inches


    private EncoderFollower left;
    private EncoderFollower right;

    private String leftCSV;
    private String rightCSV;
    
    private Encoder m_left_encoder;
    private Encoder m_right_encoder;
  
    private AnalogGyro m_gyro;
     
    // private Notifier m_follower_notifier;

    public DrivePathCommand(String leftCSV, String rightCSV) {
        requires(Robot.driveTrain);
        drivetrain = Robot.driveTrain;
        this.leftCSV = leftCSV;
        this.rightCSV = rightCSV;

        // instantiate encoders and gyros
        m_left_encoder = new Encoder(Constants.k_left_encoder_port_a, Constants.k_left_encoder_port_b);
        m_right_encoder = new Encoder(Constants.k_right_encoder_port_a, Constants.k_right_encoder_port_b);
        // if the left encoder is inversely mounted like last year, need to use these constructors instead
        //m_left_encoder = new Encoder(Constants.k_left_encoder_port_a, Constants.k_left_encoder_port_b, true, Encoder.EncodingType.k4X);
        //m_right_encoder = new Encoder(Constants.k_right_encoder_port_a, Constants.k_right_encoder_port_b, false, Encoder.EncodingType.k4X);
        m_gyro = new AnalogGyro(Constants.k_gyro_port);
    }

    @Override
    protected void initialize() {

        System.out.println("Running: " + leftCSV + ", " + rightCSV);

        double kV = 1 / MAX_VELOCITY;
        try {
            File left_csv_trajectory = new File(leftCSV);
            File right_csv_trajectory = new File(rightCSV);

            Trajectory left_trajectory = Pathfinder.readFromCSV(left_csv_trajectory);
            Trajectory right_trajectory = Pathfinder.readFromCSV(right_csv_trajectory);

            left = new EncoderFollower(left_trajectory);
            right = new EncoderFollower(right_trajectory);                
        } catch(IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }

        left.configureEncoder(m_left_encoder.get(), TICKS_PER_REVOLUTION, WHEEL_DIAMETER);
        // You must tune the PID values on the following line!
        // The first argument is the proportional gain. Usually this will be quite high
        // The second argument is the integral gain. This is unused for motion profiling
        // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
        // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
        //      trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
        // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
        left.configurePIDVA(1.0, 0.0, 0.0, kV, 0);
    
        right.configureEncoder(m_right_encoder.get(), TICKS_PER_REVOLUTION, WHEEL_DIAMETER);
        // You must tune the PID values on the following line!
        right.configurePIDVA(1.0, 0.0, 0.0, kV, 0);
        
        // No need for the notifier from example because execute method is called by the
        // scheduler automatically
        //m_follower_notifier = new Notifier(this::followPath);
        //m_follower_notifier.startPeriodic(left_trajectory.get(0).dt);
    }

    @Override
    protected void execute() {
        double left_speed = left.calculate(m_left_encoder.get());
        double right_speed = right.calculate(m_right_encoder.get());
        double heading = m_gyro.getAngle(); // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading()); // also in degrees
        // The Pathfinder.boundHalfDegrees() function simply binds a degrees angle to -180..180, 
        // making sure we don't end up with an absurdly large turn value
        double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
        double kG = kP_TURN * (-1.0/80.0);        
        double turn =  kG * heading_difference;

        drivetrain.setMotorOutputs(left_speed + turn, right_speed - turn); 
 
    }

    @Override
    protected boolean isFinished() {
        return left.isFinished() && right.isFinished();
    }

    @Override
    protected void end() {
        drivetrain.setMotorOutputs(0, 0); 
    }

    @Override
    protected void interrupted() {
        end();
    }
}
