package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import  com.qualcomm.robotcore.hardware.Servo;
@Auto(name = "autoTemplate")
public class autoTemplate extends LinearOpMode {

    private DcMotor fr;
    private DcMotor bl;
    private DcMotor fl;
    private DcMotor br;
    private DcMotor intakeMotor;
    private Servo armServoR;
    private Servo armServoL;
    private DcMotor slideMotorR;
    private DcMotor slideMotorL;
    private Servo bucketServoR;
    private Servo bucketServoL;
    private Servo claw;
    private Servo wristServo;
    private Servo topClaw;
    private Servo topWrist;
    boolean toggleWrist = false;
    boolean toggleTopWrist = false;

    boolean toggleTop = false;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float forwardSpeed;
        float strafeSpeed;
        float turnSpeed;

        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        claw = hardwareMap.get(Servo.class, "claw");
        armServoR = hardwareMap.get(Servo.class, "armServoR");
        armServoL = hardwareMap.get(Servo.class, "armServoL");
        slideMotorR = hardwareMap.get(DcMotor.class, "slideMotorR");
        slideMotorL = hardwareMap.get(DcMotor.class, "slideMotorL");
        bucketServoR = hardwareMap.get(Servo.class, "bucketServoR");
        bucketServoL = hardwareMap.get(Servo.class, "bucketServoL");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        topWrist = hardwareMap.get(Servo.class, "topWrist");
        topClaw = hardwareMap.get(Servo.class, "topClaw");
        //fr.setDirection(DcMotor.Direction.REVERSE);
        //bl.setDirection(DcMotor.Direction.REVERSE);
        armServoL.setDirection(Servo.Direction.REVERSE);
        bucketServoR.setDirection(Servo.Direction.REVERSE);
        topWrist.setDirection(Servo.Direction.REVERSE);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        slideMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();

        if (opModeIsActive()) {



                telemetry.addData("armServoR Position: ", armServoR.getPosition());
                telemetry.addData("armServoL Position: ", armServoR.getPosition());
                telemetry.addData("bucketServoR Position: ", bucketServoR.getPosition());
                telemetry.addData("bucketServoR Position: ", bucketServoR.getPosition());
                telemetry.addData("claw Position: ", claw.getPosition());
                telemetry.addData("Wrist Position", wristServo.getPosition());
                telemetry.addData("top claw", topClaw.getPosition());
                telemetry.update();

            }

        }
    }

    public void toggleTopMethod(){
        if(toggleTop){
            topClaw.setPosition(.55);
        }
        else{
            topClaw.setPosition(.1);
        }

    }


    public void raiseSlides() {
        slideMotorL.setPower(.4);
        slideMotorR.setPower(-.4);
        if(gamepad1.a){
            slideMotorL.setPower(0);
            slideMotorR.setPower(0);
        }
        sleep(3700);
        slideMotorL.setPower(0);
        slideMotorR.setPower(0);

    }
    public void lowerSlides() {
        slideMotorL.setPower(-.4);
        slideMotorR.setPower(.4);
        if(gamepad1.b){
            slideMotorL.setPower(0);
            slideMotorR.setPower(0);
        }
        sleep(3500);
        slideMotorL.setPower(0);
        slideMotorR.setPower(0);

    }
    public void transfer1(){
        bucketServoL.setPosition(0.38);
        bucketServoR.setPosition(0.38);
        topWrist.setPosition(0.155);
        wristServo.setPosition(0);
        sleep(800);
        armServoR.setPosition(0.245);
        armServoL.setPosition(0.245);
        sleep(500);
        topClaw.setPosition(0.55);
        sleep(500);
        claw.setPosition(0.05);
        sleep(1000);
        toggleTop = true;
        toggleTopMethod();
    }
    public void afterTransfer1(){
        bucketServoL.setPosition(0.37);
        bucketServoR.setPosition(0.37);
        armServoR.setPosition(0.2);
        armServoL.setPosition(0.2);
        wristServo.setPosition(0.75);
        topWrist.setPosition(0.155);
    }
    public void transfer2(){
        topClaw.setPosition(0.55);
        sleep(500);
        topWrist.setPosition(0.73);
        bucketServoL.setPosition(.8);
        bucketServoR.setPosition(.8);
        sleep(1500);
        topClaw.setPosition(.1);
        //toggleTop = false;
        //toggleTopMethod();
        sleep(1000);
    }
}
