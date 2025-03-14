package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

@Autonomous(name = "USE FourSampleAuto", group = "Auto")
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
                //go to bucket
                .afterTime(0.001, bot.savingBottomWrist())
                .afterTime(0.4,   bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(45, 61), Math.toRadians(-90))
                //drop in bucket
                .splineToLinearHeading(new Pose2d(57.5,61,Math.toRadians(-135)),Math.toRadians(135))
                .afterTime(0.01, bot.actionBucketDropAuto())
                .afterTime(0.000000000000001, bot.toIntake())
                .waitSeconds(.2)
                //intake first sample
                .strafeToLinearHeading(new Vector2d(44.5,46),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.1)
                .stopAndAdd(bot.intakeAuto())
                //
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(58, 61.75), Math.toRadians(-135))
                .stopAndAdd(bot.actionBucketDropAuto())
//
                .strafeToLinearHeading(new Vector2d(58,45.5),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .stopAndAdd(bot.intakeAuto())

                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(-145))
                .stopAndAdd(bot.actionBucketDropAuto())

                .strafeToLinearHeading(new Vector2d(58,42),Math.toRadians(-50)) // stop and add close claw and transfer etc
                .stopAndAdd( bot.autoLastSample())
                // stop and add close claw and transfer etc


                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(58, 61.75), Math.toRadians(-135))
                .stopAndAdd(bot.actionBucketDropAuto())

                .splineToLinearHeading(new Pose2d(33,17,Math.toRadians(180)),Math.toRadians(-180))
                .splineToLinearHeading(new Pose2d(11,17,Math.toRadians(180)),Math.toRadians(-180))
                .stopAndAdd(bot.actionPark())
                .waitSeconds(0.5)
                .build();

        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));
    }

}
