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
                //go to bucket
                .afterTime(0.001, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(45, 59), Math.toRadians(-90))
                //drop in bucket
                .splineToLinearHeading(new Pose2d(57,60.75,Math.toRadians(-135)),Math.toRadians(135))
                .afterTime(0.5, bot.actionBucketDrop())
                .afterTime(0.000000000000001, bot.toIntake())
                .waitSeconds(.6)
                //intake first sample
                .strafeToLinearHeading(new Vector2d(46,45),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(.2)
                //
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(57, 60.75), Math.toRadians(-135))
                .waitSeconds(0.4)
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)
//
                .strafeToLinearHeading(new Vector2d(59,44),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(0.2)

                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(54.5, 63.5), Math.toRadians(-145))
                .waitSeconds(0.4)
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(59,41.5),Math.toRadians(-50)) // stop and add close claw and transfer etc
                .stopAndAdd( bot.autoLastSample())
                .waitSeconds(0.3)
                // stop and add close claw and transfer etc


                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(57, 60.75), Math.toRadians(-135))
                .waitSeconds(0.4)
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(40, 15), Math.toRadians(-180))
                .afterTime(0.001,bot.actionPark())
                .strafeToLinearHeading(new Vector2d(11, 15), Math.toRadians(-180))
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
