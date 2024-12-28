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
    boolean toggleArm = false;

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


        slideMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        slideMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                //Sets the claw positions on top and bottom


                if (gamepad2.left_bumper) {
                    topClaw.setPosition(0.55);
                } else {
                    if(toggleTop){
                        topClaw.setPosition(0.55);
                    }
                    else{
                        topClaw.setPosition(.1);
                    }
                }

                if (gamepad2.right_bumper) {
                    claw.setPosition(0.05);
                } else {
                    claw.setPosition(0.55);
                }
                if(gamepad2.y){
                    toggleTop = !toggleTop;
                }

                if (gamepad2.a) {
                    bucketServoL.setPosition(0.37);
                    bucketServoR.setPosition(0.37);
                    topWrist.setPosition(0.165);
                    wristServo.setPosition(0);
                    sleep(800);
                    armServoR.setPosition(0.27);
                    armServoL.setPosition(0.27);
                    sleep(500);
                    topClaw.setPosition(0.55);
                    sleep(500);
                    claw.setPosition(0.05);
                    sleep(1000);
                    toggleTop = true;
                    toggleTopMethod();
                } else {
                    bucketServoL.setPosition(0.37);
                    bucketServoR.setPosition(0.37);
                    armServoR.setPosition(0.2);
                    armServoL.setPosition(0.2);
                    wristServo.setPosition(0.75);
                    topWrist.setPosition(0.155);
                }

                if (gamepad2.b) {
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

                if (gamepad2.dpad_up) {
                    raiseSlides();
                    stopAllMotors();
                } else if (gamepad2.dpad_down){
                    lowerSlides();
                    stopAllMotors();
                } else {
                    stopAllMotors();
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
            topClaw.setPosition(.55);
        }
        else{
            topClaw.setPosition(.1);
        }

    }

    public void stopAllMotors(){
        slideMotorR.setPower(0);
        slideMotorL.setPower(0);
        slideMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void raiseSlides() {
        slideMotorL.setTargetPosition(-2000);
        slideMotorR.setTargetPosition(-2000);
        slideMotorL.setPower(.4);
        slideMotorR.setPower(-.4);
        slideMotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void lowerSlides() {
        slideMotorL.setTargetPosition(1000);
        slideMotorR.setTargetPosition(1000);
        slideMotorL.setPower(.4);
        slideMotorR.setPower(-.4);
        slideMotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        stopAllMotors();

    }
}
