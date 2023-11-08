package frc.robot.Commands;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.LEDstripOne;

public class FadingRainbow extends CommandBase {
    private double m_maxTime;
    private int m_rainbowFirstPixelHue;
    private LEDstripOne m_strip;
    private Timer m_timer;

    public FadingRainbow(LEDstripOne ledStrip, double timeout) {
        m_strip = ledStrip;
        addRequirements(m_strip);
        m_timer = new Timer();
        m_maxTime = timeout;
    }

    @Override
    public void initialize() {
        m_rainbowFirstPixelHue = 0;
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        AddressableLEDBuffer ledBuffer = m_strip.getBuffer();
        // For every pixel
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            // Calculate the hue - hue is easier for rainbows because the color
            // shape is a circle so only one value needs to precess
            final int hue = (m_rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
            // Calculate the volume, or the brightness that we want to fade over time
            final int volume = (int) (128 * (1 - m_timer.get()/m_maxTime));
            // Set the value
            ledBuffer.setHSV(i, hue, 255, volume);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;

        // Set the LEDs
        m_strip.push();
    }

    @Override
    public void end(boolean interrupted) {
        if(!interrupted) {
            m_strip.turnOff();
        }
    }

    @Override
    public boolean isFinished() {
        return m_timer.hasElapsed(m_maxTime);
    }
}
