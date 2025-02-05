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

@TeleOp(name = "Main Teleop")
public class MainTeleOp extends LinearOpMode {
    private GamepadEx gp1,gp2;
    private  boolean toggleTopClaw = false;
    private  boolean toggleBottClaw = false;
    private boolean liftArm = false;
    private double driveSpeed = 1, driveMultiplier = 1;

    int slidesTarget = 0;
    ElapsedTime time = new ElapsedTime();
    Bot bot;
    private List<Action> runningActions = new ArrayList<>();
    private FtcDashboard dash = FtcDashboard.getInstance();


    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Bot.instance = null;
        int c = 2;
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
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
                if (c == 1) {
                    bot.intakeClaw.clawStraight();
                    c += 1;
                } else if (c == 2) {
                    bot.intakeClaw.clawSlanted();
                    c += 1;
                } else if (c == 3) {
                    bot.intakeClaw.setRotate0ther45Deg();
                    c += 1;
                } else if (c == 4) {
                    bot.intakeClaw.clawHorizontal();
                    c = 1;


                }
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                bot.intakeClaw.toggleClaw();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
               bot.intakeArm.Hover();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.Y)){
                runningActions.add(bot.actionBucketDrop());
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.X)){
                    runningActions.add(bot.actionIntakeSample());
                }
            if (gp2.wasJustPressed(GamepadKeys.Button.B)){
                    runningActions.add(bot.actionHighBucket());

            }
            if (gp2.wasJustPressed(GamepadKeys.Button.A)) {
                runningActions.add(bot.actionTransfer());
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
               runningActions.add(bot.actionIntakeSpecimen());
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
                bot.outtakeClaw.toggleTopClaw();
            }

            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
               runningActions.add(bot.actionHighChamber());
            }
            if(gp2.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)){
                bot.resetTeleop();
            }
            if(gp2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.1){
                runningActions.add(bot.actionClip());
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
        driveSpeed = driveMultiplier - 0.3 * gp1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        driveSpeed = Math.max(0, driveSpeed);
        Vector2d driveVector = new Vector2d(gp1.getLeftX(), -gp1.getLeftY()),
                turnVector = new Vector2d(gp1.getRightX(), 0);
        bot.driveRobotCentric(driveVector.getX() * driveSpeed,
                driveVector.getY() * driveSpeed,
                turnVector.getX() * driveSpeed
        );
    }



}