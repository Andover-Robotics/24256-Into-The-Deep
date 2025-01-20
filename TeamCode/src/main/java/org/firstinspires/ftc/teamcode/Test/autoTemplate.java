package org.firstinspires.ftc.teamcode.Test;

import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.storage;
import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.topBucket;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import  com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

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
    Bot bot;
    ElapsedTime time = new ElapsedTime();

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float forwardSpeed;
        float strafeSpeed;
        float turnSpeed;
        bot = Bot.getInstance(this);

        bot.resetEncoders();


        waitForStart();

        if (opModeIsActive()) {
            defaultPos();
            drive(-50, -50, 0.6);
            strafe(-850, 0.6);
            sleep(600);
            drive(-100,-100,.6);
            sleep(500);
            turnRight(-3800, 0.6);
            sleep(500);
            drive(310,310,.6);
            sleep(500);
            lowerSlides();
            sleep(500);
            goToOuttake();
            sleep(500);
            defaultPos();
            sleep(500);
            raiseSlides();
            drive(-1000,-1000,.6);
            turnRight(550, 0.6);
            strafe(-1390, 0.6);
            sleep(1500);


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
        bot.slides.runTo(topBucket);
    }
    public void lowerSlides(){
        bot.slides.runTo(storage);
    }
    public void transferToIntake(){
        bot.goToTransferPos(time);
    }
    public void defaultPos(){
       bot.resetEverything();
    }
    public void goToOuttake(){
        bot.goToOuttakePos(time);
    }

    /**
     * Turns the robot right by setting opposite motor targets.
     *
     * @param target The target position to turn right.
     * @param speed  The speed at which to turn.
     */


    public void turnRight(int target, double speed) {
        // Set target positions for left and right motors to rotate in opposite directions
        resetDriveEncoders();
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
        resetDriveEncoders();
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
    public void resetDriveEncoders(){
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void strafe(int target, double speed) {
        resetDriveEncoders();
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
        stopAllMotors();
    }

}


