package org.firstinspires.ftc.teamcode.Auto.pedroTuning;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.TwoWheelConstants;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        TwoWheelConstants.forwardTicksToInches = 0.0019752826287224;
        TwoWheelConstants.strafeTicksToInches = 0.0019752826287224;
        TwoWheelConstants.forwardY = -8;
        TwoWheelConstants.strafeX = -1;
        TwoWheelConstants.forwardEncoder_HardwareMapName = "par";
        TwoWheelConstants.strafeEncoder_HardwareMapName = "perp";
        TwoWheelConstants.forwardEncoderDirection = Encoder.REVERSE;
        TwoWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
        TwoWheelConstants.IMU_HardwareMapName = "imu";
        TwoWheelConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);
    }
}
