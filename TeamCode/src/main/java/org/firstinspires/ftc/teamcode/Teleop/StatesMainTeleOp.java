package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "DOESNT WORK")
public class StatesMainTeleOp extends LinearOpMode {
    private GamepadEx gp1,gp2;
    private double driveSpeed = 1, driveMultiplier = 1;

    int slidesTarget = 0;
    ElapsedTime time = new ElapsedTime();
    Bot bot;
    private List<Action> runningActions = new ArrayList<>();
    private FtcDashboard dash = FtcDashboard.getInstance();


    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Bot.instance = null;
        int c = 1;
        bot = Bot.getInstance(this);
        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);
        //bot init
        bot.prepMotors();
        bot.prepSubsystems();
        bot.resetEverything();
        bot.state = Bot.BotStates.STORAGE;
        waitForStart();
        while (opModeIsActive()) {
            /*
            driver 2 controls:
            Universal
                right bumper: opens top claw
                left bumper: opens bottom claw
                dpad up: rotates claw
                left stick: resets teleop
            Storage:
                X: intake sample
                A: transfer
                B: Intake Spec
            Transfer:
                A: outtake low bucket
                B: outtake high bucket
            Bucket:
                Y: outtake sample
            Intake Spec:
                Y: go to high chamber pos
            Clip
                Y: clip on bar
                Need to reset teleop to clip again for now
             */


            TelemetryPacket packet = new TelemetryPacket();

            // updated based on gamepads
            gp2.readButtons();

            if (gp2.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                // open bottom claw
                bot.intakeClaw.toggleClaw();
                bot.intakeClaw.clawStraight();
                c = 1;
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
                //open top claw
                bot.outtakeClaw.toggleTopClaw();

            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
                if (c == 0) {
                    bot.intakeClaw.clawStraight();
                    c += 1;
                } else if (c == 1) {
                    bot.intakeClaw.clawHorizontal();
                    c += 1;
                } else if (c == 2) {
                    bot.intakeClaw.rotate0ther45Deg();
                    c += 1;
                } else if (c == 3) {
                    bot.intakeClaw.clawSlanted();
                    c = 0;
                }
            if (bot.state == Bot.BotStates.STORAGE) {
                // in storage: intake sample, drag pos, transfer, intake spec,

                    if (gp2.wasJustPressed(GamepadKeys.Button.X)){
                        runningActions.add(bot.actionIntakeSample());
                        bot.intakeClaw.open = false;

                    }
                    if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
                        bot.intakeArm.intake();
                }
                    if (gp2.wasJustPressed(GamepadKeys.Button.A)) {
                        runningActions.add(bot.actionTransfer());
                        c=1;
                    }
                    if (gp2.wasJustPressed(GamepadKeys.Button.B)){
                        runningActions.add(bot.actionIntakeSpecimen());
                    }

            }

                if (bot.state == Bot.BotStates.TRANSFER){
                    //high bucket pos: B
                    //low bucket pos: A
                    if (gp2.wasJustPressed(GamepadKeys.Button.B)){
                        runningActions.add(bot.actionHighBucket());

                    }
                    if(gp2.wasJustPressed(GamepadKeys.Button.A)){
                        runningActions.add(bot.actionLowBucket());
                    }

                }
                if (bot.state == Bot.BotStates.BUCKET){
                    //drop in bucket: Y
                    if (gp2.wasJustPressed(GamepadKeys.Button.Y)){
                        runningActions.add(bot.actionBucketDrop());
                    }
                }
                if (bot.state == Bot.BotStates.INTAKE_SPEC){
                    // goes to clip pos: A
                    if (gp2.wasJustPressed(GamepadKeys.Button.Y)) {
                        runningActions.add(bot.actionHighChamber());
                    }
                }
                if (bot.state == Bot.BotStates.CLIP_POS){
                    // clips: A
                    if(gp2.wasJustPressed(GamepadKeys.Button.Y)){
                        runningActions.add(bot.actionClip());
                    }
                }


            }









            if(gp2.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)){
                bot.resetTeleop();
            }
            if(gp2.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON)){
                runningActions.add(bot.actionResetIntake());
            }

            drive();




            telemetry.addData("slides target", bot.slides.target);
            telemetry.addData("slides target", slidesTarget);
            telemetry.addData("slides position", bot.slides.getPosition());
            telemetry.addData("slides joystick input", gp2.getLeftY());
            telemetry.addData("left top servo pos", bot.outtakeArm.bucketServoL.getPosition());
            telemetry.addData("Right top servo pos", bot.outtakeArm.bucketServoR.getPosition());
            telemetry.addData("gp2RightY", gp2.getRightY());
            telemetry.addData("Manual Power", bot.slides.manualPower);
            telemetry.addData("PIDF Controller SetPoint", bot.slides.controller.getSetPoint());
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
        driveSpeed = driveMultiplier - 0.7 * gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        driveSpeed = Math.max(0, driveSpeed);
        Vector2d driveVector = new Vector2d(gp1.getLeftX(), -gp1.getLeftY()),
                turnVector = new Vector2d(-gp1
                        .getRightX(), 0);
        bot.driveRobotCentric(driveVector.getX() * driveSpeed,
                driveVector.getY() * driveSpeed,
                turnVector.getX() * driveSpeed
        );
    }



}