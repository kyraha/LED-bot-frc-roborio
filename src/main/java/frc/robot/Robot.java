// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Commands.FadingRainbow;
import frc.robot.Subsystems.LEDstripOne;

public class Robot extends TimedRobot {
    LEDstripOne m_stripOne = new LEDstripOne(9);

    @Override
    public void robotInit() {
        ShuffleboardTab tab = Shuffleboard.getTab("LED");
        tab.add(new FadingRainbow(m_stripOne, 3.0));
    }

    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

}
