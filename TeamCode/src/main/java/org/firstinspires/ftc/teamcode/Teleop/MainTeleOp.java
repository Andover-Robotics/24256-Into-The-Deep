package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.storage;
import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.topBucket;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import  com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Test.IntakeTest;

@TeleOp(name = "Main Teleop")
public class MainTeleOp extends LinearOpMode {
    private GamepadEx gp1,gp2;
    private  boolean toggleTopClaw = false;
    private  boolean toggleBottClaw = false;
    private boolean liftArm = false;

    int slidesTarget = 0;
    ElapsedTime time = new ElapsedTime();
    Bot bot;
    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = Bot.getInstance(this);
        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);
        bot.prepMotors();
        bot.resetEverything();
        waitForStart();
        while (opModeIsActive()) {
            gp2.readButtons();

            if (gp2.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                toggleBottClaw = !toggleBottClaw;
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
                toggleTopClaw = !toggleTopClaw;
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.X)){
                liftArm = !liftArm;
            }

            // opens both bottom and top claws with toggles.
            if (toggleBottClaw) {
                bot.intakeClaw.openClaw();
            } else {
                bot.intakeClaw.closeClaw();
            }
            if (toggleTopClaw) {
                bot.outtakeClaw.outtakeClawOpen();
            } else {
                bot.outtakeClaw.outtakeClawClose();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.A)){
                bot.goToTransferPos(time);
            } else {
                bot.resetIntake();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.B)){
                bot.goToOuttakePos(time);
            } else {
                bot.resetOuttake();
            }
            if (liftArm){
                bot.intakeArm.armToUpPos();
            }
//            if (gp2.wasJustPressed((GamepadKeys.Button.DPAD_UP))) {
//                bot.slides.runTo(topBucket);
//            }
//            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
//                bot.slides.runTo(storage);
//            }

            if (gp2.wasJustPressed((GamepadKeys.Button.DPAD_UP))) {
                slidesTarget-= 50;
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
                slidesTarget+= 50;
            }

            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
                bot.slides.runTo(slidesTarget);
            }
            if (Math.abs(gp2.getLeftY()) > 0.05){
                bot.slides.runToManual(gp2.getLeftY());
            }

            drive();
            telemetry.addData("slides target", bot.slides.target);
           telemetry.addData("slides target", slidesTarget);
           telemetry.addData("slides postion", bot.slides.getPosition());
           telemetry.update();
           bot.slides.periodic();
        }



    }
    public void drive() {
        gp1.readButtons();
        bot.prepMotors();
        Vector2d driveVector;
        driveVector = new Vector2d(gp1.getLeftX(), -gp1.getLeftY());
        Vector2d turnVector;
        turnVector = new Vector2d(-gp1.getRightX(), 0);
        bot.driveRobotCentric(driveVector.getX()*0.7, driveVector.getY()*0.7, turnVector.getX()/1.7);

    }



}