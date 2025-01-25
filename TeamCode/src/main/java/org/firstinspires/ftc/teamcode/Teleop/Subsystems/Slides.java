package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


@Config

public class Slides {
    public  final MotorEx slideMotorR, slideMotorL;
    private PIDFController controller;
    public static double p = 0.015, i = 0, d = 0, f =0, staticF = 0.25;
    //tune if you have time, supposedly it will work well enough without tuning but likely will help.
    private final double tolerance = 10, powerUp = 0.1, powerDown = 0.05, powerMin =0.1, manualDivide = 1 ;
    public  double target = 0;
    private double power;
    private final OpMode opMode;
    private double manualPower = 0;
    public boolean goingDown = false;

    public static int storage = 0, topBucket = -3000, failSafe = -500;
    //tune top bucket value very carefully
    private double profiler_init_time = 0;


    MotionProfiler profiler = new MotionProfiler(20000,30000);
    public Slides (OpMode opmode) {
        slideMotorL = new MotorEx(opmode.hardwareMap, "slideMotorL", Motor.GoBILDA.RPM_312);
        slideMotorR = new MotorEx(opmode.hardwareMap, "slideMotorR", Motor.GoBILDA.RPM_312);

        controller = new PIDFController(p, i, d, f);
        controller.setTolerance(tolerance);
        controller.setSetPoint(0);

        slideMotorR.setInverted(false);
        slideMotorL.setInverted(true);

        slideMotorL.setRunMode(Motor.RunMode.RawPower );
        slideMotorL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        slideMotorR.setRunMode(Motor.RunMode.RawPower);
        slideMotorR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        this.opMode = opmode;
    }
    public void runTo(double pos) {
        slideMotorR.setRunMode(Motor.RunMode.RawPower);
        slideMotorR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        slideMotorL.setRunMode(Motor.RunMode.RawPower);
        slideMotorL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        controller = new PIDFController(p, i, d, f);
        controller.setTolerance(tolerance);
        resetProfiler();
        profiler.init(slideMotorL.getCurrentPosition(),pos);
        profiler_init_time = opMode.time;

        goingDown = pos> target;
        target = pos;

        /* if (manualPower == 0) {
            resetProfiler();
            profiler.init(slideMotorL.getCurrentPosition(),pos);
            profiler_init_time = opMode.time;

            goingDown = pos> target;
            target = pos;
        } */
    }
    public void runToTopBucket(){
        runTo(topBucket);
        }
    public void runToStorage() {
        runTo(storage);
    }
    public void runSlides(double power) {
            runToManual(power);
            periodic();
        }

    public void runToManual(double manual){
        if (manualPower > powerMin || manualPower < -powerMin) {
            manualPower = -manual;
        } else {
            manualPower = 0;
        }
        }
    public void periodic() {
        slideMotorL.setInverted(false);
        slideMotorR.setInverted(true);
        controller.setPIDF(p, i, d, f);
        double dt = opMode.time - profiler_init_time;
        if (!profiler.isOver()) {

            controller.setSetPoint(profiler.profile_pos(dt));
            power = powerUp * controller.calculate(slideMotorL.getCurrentPosition());
            if (goingDown) {
                power = powerDown * controller.calculate(slideMotorL.getCurrentPosition());
            }
            slideMotorR.set(power);
            slideMotorL.set(power);


        } else {
            if (profiler.isDone()) {
                profiler = new MotionProfiler(30000, 20000);
            }

            if (manualPower != 0) {

                controller.setSetPoint(slideMotorL.getCurrentPosition());
                slideMotorR.set(manualPower / manualDivide);
                slideMotorL.set(manualPower / manualDivide);

            } else {
                power = staticF * controller.calculate(slideMotorL.getCurrentPosition());
                slideMotorR.set(power);
                slideMotorL.set(power);
            }


        }
    }


    public void resetProfiler() {
        profiler = new MotionProfiler(20000,30000);
    }


    public int getPosition() {
        return slideMotorL.getCurrentPosition();
    }
public void resetSlideEncoders(){
        slideMotorL.resetEncoder();
        slideMotorR.resetEncoder();
}
}


