package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import  com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name = "Bucket Auto")
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
        bucketServoL.setDirection(Servo.Direction.REVERSE);
        topWrist.setDirection(Servo.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);


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
            afterTransfer1();
            drive(-300, -300, 0.6);
            strafe(-1000, 0.6);
            sleep(600);
            drive(-100,-100,.6);
            sleep(500);
            turnRight(-3800, 0.6);
            sleep(500);
            drive(300,300,.6);
            sleep(500);
            lowerSlides();
            sleep(500);
            drive(270, 270, .6);
            transfer2();
            sleep(500);
            afterTransfer1();
            sleep(500);
            raiseSlides();
            sleep(500);
            /* drive(300, 300, .6);
            afterTransfer1();
            raiseSlides();
            turnRight(550, 0.6);
            strafe(750, 0.6);
            drive(900, 800, 0.6);
            strafe(1390, 0.6);
            turnRight(2130, 0.6);
            drive(-500, -500, 0.6);
            raiseSlides();
            sleep(1700);*/


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

    public void toggleTopMethod(){
        if(toggleTop){
            topClaw.setPosition(.65);
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
        sleep(3500);



    }
    public void lowerSlides() {
        slideMotorL.setPower(-.4);
        slideMotorR.setPower(.4);
        if(gamepad1.b){
            slideMotorL.setPower(0);
            slideMotorR.setPower(0);
        }
        sleep(3600);
        slideMotorL.setPower(-.09);
        slideMotorR.setPower(0.09);


    }
    public void transfer1(){
        bucketServoL.setPosition(0.38);
        bucketServoR.setPosition(0.38);
        topWrist.setPosition(0.15);
        // wrist less is more up

        wristServo.setPosition(0.18);
        sleep(1500);
        armServoR.setPosition(0.62);
        armServoL.setPosition(0.62);
        sleep(500);
        sleep(500);
        topClaw.setPosition(0.6);
        sleep(500);
        claw.setPosition(0.05);
        sleep(1000);
        toggleTop = true;
        toggleTopMethod();
    }
    public void afterTransfer1(){
        bucketServoL.setPosition(0.58);
        bucketServoR.setPosition(0.58);
        armServoR.setPosition(0.43);
        armServoL.setPosition(0.43);
        wristServo.setPosition(0.8);
        topWrist.setPosition(0.12);
    }
    public void transfer2(){
        topClaw.setPosition(0.7);
        sleep(500);
        topWrist.setPosition(0.53);
        bucketServoL.setPosition(.9);
        bucketServoR.setPosition(.9);
        sleep(1500);
        topClaw.setPosition(.1);
        //toggleTop = false;
        //toggleTopMethod();
        sleep(1000);
    }

    /**
     * Turns the robot right by setting opposite motor targets.
     *
     * @param target The target position to turn right.
     * @param speed  The speed at which to turn.
     */


    public void turnRight(int target, double speed) {
        // Set target positions for left and right motors to rotate in opposite directions
        fl.setTargetPosition(target);
        bl.setTargetPosition(target);
        fr.setTargetPosition(-target);
        br.setTargetPosition(-target);

        // Set motors to RUN_TO_POSITION mode
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor power
        fl.setPower(speed);
        bl.setPower(speed);
        fr.setPower(speed);
        br.setPower(speed);

        // Wait until motors reach the target position
        while (opModeIsActive() && (fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy())) {
            telemetry.addData("Status", "Turning Right...");
            telemetry.update();
        }

        // Stop all motors
        stopAllMotors();
    }


    public void drive(int leftTarget, int rightTarget, double speed) {

        fl.setTargetPosition(leftTarget);
        bl.setTargetPosition(leftTarget);
        fr.setTargetPosition(rightTarget);
        br.setTargetPosition(rightTarget);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setPower(speed);
        bl.setPower(speed);
        fr.setPower(speed);
        br.setPower(speed);

        while (opModeIsActive() && (fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy())) {
            telemetry.addData("Status", "Driving to position...");
            telemetry.update();
        }

        stopAllMotors();
    }

    public void strafe(int target, double speed) {
        fl.setTargetPosition(-target);
        bl.setTargetPosition(target);
        fr.setTargetPosition(target);
        br.setTargetPosition(-target);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setPower(speed);
        bl.setPower(speed);
        fr.setPower(speed);
        br.setPower(speed);

        while (opModeIsActive() && (fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy())) {
            telemetry.addData("Status", "Strafing to position...");
            telemetry.update();
        }
        stopAllMotors();
    }


    public void stopAllMotors() {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnLeft(int target, double speed) {
        // Set target positions for left and right motors to rotate in opposite directions
        fl.setTargetPosition(-target);
        bl.setTargetPosition(-target);
        fr.setTargetPosition(target);
        br.setTargetPosition(target);

        // Set motors to RUN_TO_POSITION mode
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor power
        fl.setPower(speed);
        bl.setPower(speed);
        fr.setPower(speed);
        br.setPower(speed);
    }

}


