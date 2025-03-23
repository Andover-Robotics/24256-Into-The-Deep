package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Auto.ActionHelper;
import org.firstinspires.ftc.teamcode.Auto.MecanumDrive;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Main Teleop OLD", group = "Teleop")
public class OldMainTeleOp extends LinearOpMode {
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
        bot.prepMotors();
        bot.prepSubsystems();
        bot.resetEverything();
        waitForStart();
        while (opModeIsActive()) {


            TelemetryPacket packet = new TelemetryPacket();

            // updated based on gamepads
            gp2.readButtons();
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
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                bot.intakeClaw.toggleClaw();
                bot.intakeClaw.clawStraight();
                c = 1;
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
                bot.intakeArm.intake();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.Y)){
                runningActions.add(bot.actionBucketDrop());
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.X)){
                runningActions.add(bot.actionIntakeSample());
                bot.intakeClaw.open = false;

            }
            if (gp2.wasJustPressed(GamepadKeys.Button.B)){
                runningActions.add(bot.actionHighBucket());

            }
            if (gp2.wasJustPressed(GamepadKeys.Button.A)) {
                runningActions.add(bot.actionTransfer());
                c=1;
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
                runningActions.add(bot.actionIntakeSpecimen());
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
                bot.outtakeClaw.toggleTopClaw();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
                runningActions.add(bot.actionHighChamber());
            }

            if (gp1.wasJustPressed(GamepadKeys.Button.A)) {
                Bot.instance = null;
                Pose2d initialPose = new Pose2d(-60, 64, Math.toRadians(-90));
                MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

                Action runAuto = drive.actionBuilder(new Pose2d(-60, 64, Math.toRadians(-90)))
                        .afterTime(0.0001, bot.autoSpecimen())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-46,60))
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-46,67.5))
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-7,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-7,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.2)

                        .afterTime(0.4,bot.autoSpecimen())
                        .setReversed(true)
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-49.5, 67), Math.toRadians(80))
                        .waitSeconds(0.3)
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(11,45),drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(11,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.25)

                        .afterTime(0.4,bot.autoSpecimen())
                        .setReversed(true)
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-49.5, 67), Math.toRadians(80))
                        .waitSeconds(0.3)
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .afterTime(0.1, bot.iDontLikeThis())
                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-10,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-10,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.25)

                        .afterTime(0.4,bot.autoSpecimen())
                        .setReversed(true)
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-49.5, 67), Math.toRadians(80))
                        .waitSeconds(0.3)
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .afterTime(0.1, bot.iDontLikeThisPart2())
                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(13,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(13,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.25)

                        .afterTime(0.4,bot.autoSpecimen())
                        .setReversed(true)
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-49.5, 67), Math.toRadians(80))
                        .waitSeconds(0.3)
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(1,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(1,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(9,30.5), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-7,30.5), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(9,30.5), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))


                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.25)

                        .afterTime(0.4,bot.autoSpecimen())
                        .setReversed(true)
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-49.5, 67), Math.toRadians(80))
                        .waitSeconds(0.3)
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(5,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(5,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.25)

                        .afterTime(0.4,bot.autoSpecimen())
                        .setReversed(true)
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-49.5, 67), Math.toRadians(80))
                        .waitSeconds(0.3)
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(3,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(3,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.25)

                        .afterTime(0.4,bot.autoSpecimen())
                        .setReversed(true)
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(-49.5, 67), Math.toRadians(80))
                        .waitSeconds(0.3)
                        .stopAndAdd(bot.autoIntakeSpecimen())

                        .stopAndAdd(bot.actionHighChamberAuto())
                        .strafeToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(0,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .splineToConstantHeading(new com.acmerobotics.roadrunner.Vector2d(0,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                        .stopAndAdd(bot.actionAutoClip())
                        .stopAndAdd(bot.actionSlidesLower())
                        .waitSeconds(0.25)
                                .build();
                Actions.runBlocking(
                        new ActionHelper.RaceParallelCommand(
                                bot.actionPeriodic(),
                                runAuto
                        ));
            }

            if(gp2.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)){
                bot.resetTeleop();
            }

            if(gp2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>0.4){
                runningActions.add(bot.actionClip());
            }
            if( gp2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0.4){
                runningActions.add(bot.actionLowBucket());

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
        Vector2d driveVector = new Vector2d(-gp1.getLeftX(), gp1.getLeftY()),
                turnVector = new Vector2d(gp1
                        .getRightX(), 0);
        bot.driveRobotCentric(driveVector.getX() * driveSpeed,
                driveVector.getY() * driveSpeed,
                turnVector.getX() * driveSpeed
        );
    }



}