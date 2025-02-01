package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

@Autonomous(name = "Bucket Auto")
public class UPDATEDBUCKETAUTO extends LinearOpMode {
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
                .stopAndAdd(bot.armFlip())
                .waitSeconds(0.2)

                .afterTime(0.01, bot.actionHighBucket())
                //.strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(-135))
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(47.5,41),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(.2)

                .afterTime(0.01, bot.actionHighBucket())
                //.strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(-135))
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(57.5,41),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(0.2)

                .afterTime(0.01, bot.actionHighBucket())
                //.strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(-135))
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(62.3,41),Math.toRadians(-65)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.autolastsample())
                .waitSeconds(0.2)

                .afterTime(0.01, bot.actionHighBucket())
                //.strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(-135))
                .stopAndAdd(bot.actionBucketDrop())
                .waitSeconds(.2)

                .strafeToLinearHeading(new Vector2d(45, 20), Math.toRadians(90))
                .stopAndAdd(bot.actionSlidesLower())
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
