package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

@Autonomous(name = "USE FourSampleAuto")
public class FourSample extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException{
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();
        Pose2d initialPose = new Pose2d(38,64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(38,63, Math.toRadians(-90)))
                .afterTime(0.1, bot.actionHighBucket())
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(53, 60), Math.toRadians(-135))
                .afterTime(0.1, bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(47,44),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(.2)

                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(53, 60), Math.toRadians(-135))
                .waitSeconds(0.8)
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(57.5,44),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(0.2)

                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(53, 60), Math.toRadians(-135))
                .waitSeconds(1.5)
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(60,43.5),Math.toRadians(-50)) // stop and add close claw and transfer etc
                .afterTime(0.2, bot.autoLastSample())
                .waitSeconds(1.5)
                .strafeToLinearHeading(new Vector2d(58,43.5),Math.toRadians(-50))
                .waitSeconds(1.9)
                // stop and add close claw and transfer etc


                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(53, 60), Math.toRadians(-135))
                .waitSeconds(1)
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(40, 30), Math.toRadians(-180))
                //.stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(2)
                .build();

        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));
    }

}
