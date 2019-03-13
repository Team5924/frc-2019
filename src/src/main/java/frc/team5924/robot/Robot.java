package frc.team5924.robot;

import edu.wpi.first.wpilibj.Compressor;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5924.robot.subsystems.*;
import frc.team5924.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi; //drivetrain joystick
  public static OI e_oi; //elevator joystick
  public static DriveTrain driveTrain;
  public static Elevator elevator;
  public static Dory dory;
  public static HandOfGod handOfGod;
  public static Limelight limeLight;
  //public static HatchGrabber hatchGrabber;
  //public static Compressor c;

  public static final double ftpersec = 14.39;
  public static final double ftPerSecWithFriction = 11.66; //actual roughly 8.6ft/sec
  private Command m_autonomousCommand;
  private Command m_teleopCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<String> m_chooser = new SendableChooser<>();
  SendableChooser<Command> m_chooseCommand = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI(1);
    e_oi = new OI(2);
    driveTrain = new DriveTrain();
    elevator = new Elevator();
    dory = new Dory();
    handOfGod = new HandOfGod();
    limeLight = new Limelight();
   //hatchGrabber = new HatchGrabber();
    //c = new Compressor();
    //c.setClosedLoopControl(false);

    // chooser.addObject("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);
    // Create a droplist on the dashboard to pick the left or right path
		m_chooser.addDefault("Right Auto", "R");
    m_chooser.addObject("Left Auto", "L");
    // Test Auto just moves a straight path
		m_chooser.addObject("Test Auto", "T");
    SmartDashboard.putData("Auto mode", m_chooser);
    
    // Present a menu of commands on the dashboard in teleop mode
    m_chooseCommand.addDefault("Drive Command", new DriveCommand());
    m_chooseCommand.addObject("Dory Command", new DoryCommand());
    m_chooseCommand.addObject("Elevator Command", new ElevatorCommand());
    m_chooseCommand.addObject("HandOfGod Command", new HandOfGodCommand());
    m_chooseCommand.addObject("LimeLightTarget Command", new LimeLightTargetCommand());    
    SmartDashboard.putData("Teleop mode", m_chooseCommand);    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    //c.setClosedLoopControl(false);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    String selectedAuto = m_chooser.getSelected();
    // may need to pass other game data to AutoCommand like last year
    m_autonomousCommand = new AutoCommand(selectedAuto);

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // Instantiate the teleop command chosen
    m_teleopCommand = m_chooseCommand.getSelected();
    // schedule the teleop command (example)
    if (m_teleopCommand != null) {
      m_teleopCommand.start();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}