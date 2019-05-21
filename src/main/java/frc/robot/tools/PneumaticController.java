package frc.robot.tools;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.RobotMap;

public class PneumaticController {
    private final Compressor mCompressor;
    private final AnalogInput mPressureSensor;

    private final double DEFAULT_VOLTS = 4.52;
    private final double SLOPE = 250.0;
    private final double Y_INTERCEPT = -25.0;

    public PneumaticController(Compressor compressor, AnalogInput pressureSensor) {
        mCompressor = compressor;
        mPressureSensor = pressureSensor;
    }

    public static PneumaticController create() {
        Compressor compressor = new Compressor(RobotMap.compressor);
        AnalogInput pressureSensor = new AnalogInput(RobotMap.pressueSensor);

        return new PneumaticController(compressor, pressureSensor);
    }

    public void turnOffCompressor() {
        mCompressor.stop();
    }

    public void turnOnCompressor() {
        mCompressor.start();
    }

    public boolean getCompressorStatus() {
        return mCompressor.enabled();
    }

    public double getTankPressure() {
        return SLOPE * (mPressureSensor.getVoltage() / DEFAULT_VOLTS) + Y_INTERCEPT;
    }

    public double getPressureSensorVolts() {
        return mPressureSensor.getVoltage();
    }
}
