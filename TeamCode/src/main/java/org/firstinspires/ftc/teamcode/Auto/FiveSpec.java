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

        Action runAuto = drive.actionBuilder(new Pose2d(-24, 64, Math.toRadians(-90)))
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToLinearHeading(new Vector2d(6, 29), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-27,57))
                .stopAndAdd(bot.actionAutoClip())

                .afterTime(0.01,bot.actionSlidesLower())
                //clip preload
                .afterTime(1.4,bot.actionSweep())
                .strafeToConstantHeading(new Vector2d(-20,43))
                .splineToLinearHeading(new Pose2d(-42, 39,Math.toRadians(-120)),Math.toRadians(55))
                .splineToLinearHeading(new Pose2d(-44,64,Math.toRadians(140)),Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,75))
                .stopAndAdd(bot.armUp())
                .afterTime(0.9,bot.actionSweep())
                .strafeToLinearHeading(new Vector2d(-52,32),Math.toRadians(-120))
                .afterTime(0.1, bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-51.5,62),Math.toRadians(120), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,75))
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-56,30,Math.toRadians(-120)), Math.toRadians(55))
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-56,64,Math.toRadians(140)), Math.toRadians(75))
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-50,67,Math.toRadians(-90)), Math.toRadians(90))
                .stopAndAdd(bot.armUp())





                //pushAuto specimen into obs zone

                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(1,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(1,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.2)

                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                //.strafeToLinearHeading(new Vector2d(-47, 59), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80)).splineToConstantHeading(new Vector2d(-47.5,67),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.setReversed(true)
                //.splineToConstantHeading(new Vector2d(-47,67),Math.toRadians(-90))
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-49.5, 67), Math.toRadians(80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-1.5,45),drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-1.5,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                //.strafeToLinearHeading(new Vector2d(-8, 45), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 45, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 67, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-49.5, 67), Math.toRadians(80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //fourth specimen

                .afterTime(0.4,bot.autoSpecimen())
                //.strafeToLinearHeading(new Vector2d(-8, 45), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 45, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                //.splineToLinearHeading(new Pose2d(-50, 67, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-25,80))
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-49.5, 67), Math.toRadians(80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-6,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .strafeToConstantHeading(new Vector2d(-15, 50), drive.defaultVelConstraint, new ProfileAccelConstraint(-80,200))
                .afterTime(0.001,bot.toIntake())
                .strafeToLinearHeading(new Vector2d(-50, 64), Math.toRadians(180), drive.defaultVelConstraint, new ProfileAccelConstraint(-80,200))
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





