package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.failSafe;
import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.storage;
import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.topBucket;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import  com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Test.IntakeTest;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Main Teleop")
public class MainTeleOp extends LinearOpMode {
    private GamepadEx gp1,gp2;
    private  boolean toggleTopClaw = false;
    private  boolean toggleBottClaw = false;
    private boolean liftArm = false;

    int slidesTarget = 0;
    ElapsedTime time = new ElapsedTime();
    Bot bot;
    private List<Action> runningActions = new ArrayList<>();
    private FtcDashboard dash = FtcDashboard.getInstance();


    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = Bot.getInstance(this);
        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);
        bot.prepMotors();
        bot.prepSubsystems();
        bot.resetEverything();
        waitForStart();
        while (opModeIsActive()) {

            TelemetryPacket packet = new TelemetryPacket();

            // updated based on gamepads
            gp2.readButtons();

            if (gp2.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                bot.intakeClaw.toggleClaw();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.X)){
                runningActions.add(bot.toIntake());
            }

            if (gp2.wasJustPressed(GamepadKeys.Button.A)) {
                runningActions.add(bot.actionTransfer());
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.B)){
               runningActions.add(bot.actionHighBucket());
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.Y)){
                runningActions.add(bot.actionRelease());
            }
            if(gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
                bot.actionStorage();
            }
            // remember PLEASE raise at the start
            if (gp2.wasJustPressed((GamepadKeys.Button.DPAD_UP))) {
               bot.slides.runTo(failSafe);
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
               bot.slides.runTo(storage);
            }

            if (Math.abs(gp2.getLeftY()) > 0.05){
                bot.slides.runToManual(gp2.getLeftY());
            }

            drive();
            telemetry.addData("slides target", bot.slides.target);
            telemetry.addData("slides target", slidesTarget);
            telemetry.addData("slides postion", bot.slides.getPosition());
            telemetry.addData("slides joywtick input", gp2.getLeftY());
            telemetry.addData("left top servo pos", bot.outtakeArm.bucketServoL.getPosition());
            telemetry.addData("Right top servo pos", bot.outtakeArm.bucketServoR.getPosition());
            telemetry.update();
            bot.slides.periodic();
            // update running actions
            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;

            dash.sendTelemetryPacket(packet);
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