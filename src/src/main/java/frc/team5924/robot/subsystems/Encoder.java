package frc.team5924.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Encoder
{
    int ticksPerRev = 4096;
    double distancePerRevolution;
    int pulseWidthPos;
    public Encoder(TalonSRX talon)
    {
        distancePerRevolution = Math.PI;
        pulseWidthPos = talon.getSensorCollection().getPulseWidthPosition();
    }

    public void driveDistance(double inches)
    {
        
    }
}