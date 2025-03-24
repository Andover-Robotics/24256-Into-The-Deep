package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;


@Autonomous(name = "fivespec", group = "Auto")
public class FiveSpec extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();

        Pose2d initialPose = new Pose2d(-24, 64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
//heading, tangent
        Action runAuto = drive.actionBuilder(new Pose2d(-24, 64, Math.toRadians(-90)))
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToLinearHeading(new Vector2d(6.6, 29), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,90))
                .stopAndAdd(bot.actionAutoClip())
                .afterTime(0.01,bot.actionSlidesLower())
                //clip preload
                .afterTime(0.55,bot.actionSweep())
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-40, 39,Math.toRadians(-120)),Math.toRadians(105), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .splineToLinearHeading(new Pose2d(-42,64,Math.toRadians(140)),Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .stopAndAdd(bot.armUp())
                .setReversed(true)
                .afterTime(0.9,bot.actionSweep())
                .splineToLinearHeading(new Pose2d(-52,32,Math.toRadians(-145)), Math.toRadians(105), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .setReversed(true)
                .afterTime(0.1, bot.autoSpecimen())
                .splineToLinearHeading(new Pose2d(-53,62,Math.toRadians(120)), Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .afterTime(0.1, bot.armUp())
                .setReversed(true)
                .afterTime(0.3,bot.actionSweep())
                .splineToLinearHeading(new Pose2d(-56.5,30,Math.toRadians(-160)), Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .splineToLinearHeading(new Pose2d(-56.5,64,Math.toRadians(160)), Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-38,67,Math.toRadians(-90)), Math.toRadians(200), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))

                //pushAuto specimen into obs zone

                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .setReversed(false)
                .splineToConstantHeading(new Vector2d(1,30.5),Math.toRadians(140), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,90))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.2)

                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                //.strafeToLinearHeading(new Vector2d(-47, 59), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80)).splineToConstantHeading(new Vector2d(-47.5,67),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.setReversed(true)
                //.splineToConstantHeading(new Vector2d(-47,67),Math.toRadians(-90))
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-38, 67), Math.toRadians(80), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,95))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .setReversed(false)
                .splineToConstantHeading(new Vector2d(-1,30.5),Math.toRadians(140), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,95))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                //.strafeToLinearHeading(new Vector2d(-8, 45), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 45, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 67, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-38, 67), Math.toRadians(80), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .setReversed(false)
                .splineToConstantHeading(new Vector2d(-24,30.5),Math.toRadians(150), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //fourth specimen

                .afterTime(0.4,bot.autoSpecimen())
                //.strafeToLinearHeading(new Vector2d(-8, 45), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 45, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 67, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-38, 67), Math.toRadians(80), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .setReversed(false)
                .splineToConstantHeading(new Vector2d(-6,30.5),Math.toRadians(160), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .strafeToConstantHeading(new Vector2d(-15, 50), drive.defaultVelConstraint, new ProfileAccelConstraint(-90,200))
                .afterTime(0.001,bot.toIntake())
                .strafeToLinearHeading(new Vector2d(-50, 64), Math.toRadians(180), drive.defaultVelConstraint, new ProfileAccelConstraint(-90,200))
                //fifth

                        .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





