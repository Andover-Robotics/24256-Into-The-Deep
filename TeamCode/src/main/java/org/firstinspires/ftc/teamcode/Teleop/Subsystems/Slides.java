package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.robotcore.external.Telemetry;


@Config
@TeleOp
public class Slides {
    private MotorEx slideMotorR, slideMotorL;
    private PIDFController controller;
    public static double p = 0, i = 0, d = 0, f =0;
    //need to add motion profiler but I don't know how.
public Slides (OpMode opMode) {
    slideMotorL = new MotorEx(opMode.hardwareMap, "slideMotorL", Motor.GoBILDA.RPM_312);
    slideMotorR = new MotorEx(opMode.hardwareMap, "slideMotorR", Motor.GoBILDA.RPM_312);

    controller = new PIDFController(p, i, d, f);

    slideMotorR.setInverted(true);
    slideMotorL.setInverted(false);

    slideMotorL.setRunMode(Motor.RunMode.RawPower );
    slideMotorL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    slideMotorR.setRunMode(Motor.RunMode.RawPower);
    slideMotorR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
}

}
