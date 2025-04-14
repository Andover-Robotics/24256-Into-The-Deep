package org.firstinspires.ftc.teamcode.Auto.pedroTuning;

import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.Localizers;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {

        FollowerConstants.localizers = Localizers.TWO_WHEEL;

        FollowerConstants.mass = 10;

        FollowerConstants.leftFrontMotorName = "fl";
        FollowerConstants.rightFrontMotorName = "fr";
        FollowerConstants.leftRearMotorName = "bl";
        FollowerConstants.rightRearMotorName = "br";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

    }
}
