package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import  com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "Servo Test")
public class IntakeTest extends LinearOpMode {

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

    private boolean toggleTop = false;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double forwardSpeed;
        double strafeSpeed;
        double turnSpeed;

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
            while (opModeIsActive()) {
                // Remember, Y stick value is reversed
                forwardSpeed = gamepad1.left_stick_y * .7;
                // Factor to counteract imperfect strafing
                strafeSpeed = -gamepad1.left_stick_x *-.7;
                turnSpeed = gamepad1.right_stick_x *.7;

                // Make sure your ID's match your configuration
                fl.setPower(-forwardSpeed - strafeSpeed + turnSpeed);
                bl.setPower(-forwardSpeed - strafeSpeed - turnSpeed);
                fr.setPower(forwardSpeed - strafeSpeed + turnSpeed);
                br.setPower(forwardSpeed - strafeSpeed - turnSpeed);

                telemetry.addData("game controller ", gamepad1.left_stick_x);

                telemetry.addData("game controller ", gamepad1.left_stick_x);

                //Sets the claw positions on top and bottom


                if (gamepad2.left_bumper) {
                    toggleTop = !toggleTop;
                    toggleTopMethod();
                }

                if (gamepad2.right_bumper) {
                    claw.setPosition(0.05);
                } else {
                    claw.setPosition(0.513);
                }


                if (gamepad2.a) {
                    transfer1();
                } else if (gamepad2.right_trigger>0) {
                    armServoL.setPosition(.3);
                    armServoR.setPosition(.3);
                } else {
                    afterTransfer1();
                }

                if (gamepad2.b) {
                    transfer2();
                }


                if (gamepad2.dpad_up) {
                    raiseSlides();
                } else if (gamepad2.dpad_down){
                    lowerSlides();
                } else {
                    slideMotorR.setPower(0);
                    slideMotorL.setPower(0);
                }





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
            topClaw.setPosition(.7);
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
        sleep(3600);
        slideMotorL.setPower(.01);
        slideMotorR.setPower(-0.01);
        sleep(500);
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
        sleep(3600);
        slideMotorL.setPower(0.5);
        slideMotorR.setPower(-0.5);

    }
    public void transfer1(){
        bucketServoL.setPosition(0.43);
        bucketServoR.setPosition(0.43);
        topWrist.setPosition(0.155);
        // wrist less is more up

        wristServo.setPosition(0.01);
        sleep(1000);
        armServoR.setPosition(0.26);
        armServoL.setPosition(0.26);
        sleep(500);
        topClaw.setPosition(0.7);
        sleep(500);
        claw.setPosition(0.05);
        sleep(1000);
        toggleTop = true;
        toggleTopMethod();
    }
    public void afterTransfer1(){
        bucketServoL.setPosition(0.41);
        bucketServoR.setPosition(0.41);
        armServoR.setPosition(0.2);
        armServoL.setPosition(0.2);
        wristServo.setPosition(0.55);
        topWrist.setPosition(0.155);
    }
    public void transfer2(){
        topClaw.setPosition(0.7);
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
