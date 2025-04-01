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
                .afterTime(0.001, bot.savingBottomWrist())
                .afterTime(0.4,   bot.actionHighBucketAuto())
                .strafeToLinearHeading(new Vector2d(45, 61), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(58.5,62,Math.toRadians(-135)),Math.toRadians(135))
                .afterTime(0.01, bot.actionBucketDropAuto())
                //Outtake preload
                .afterTime(0.000000000000001, bot.toIntake())
                .waitSeconds(.2)
                .strafeToLinearHeading(new Vector2d(43.4,44),Math.toRadians(-80)) // stop and add close claw and transfer etc
                .waitSeconds(.1)
                .stopAndAdd(bot.intakeAuto())
                //intake second sample
                .afterTime(0.01, bot.actionHighBucketAuto())
                .strafeToLinearHeading(new Vector2d(57.75, 62.75), Math.toRadians(-135))
                .stopAndAdd(bot.actionBucketDropAuto())
                .waitSeconds(0.2)
                //outtake second sample
                .strafeToLinearHeading(new Vector2d(57,43.5),Math.toRadians(-80)) // stop and add close claw and transfer etc
                .stopAndAdd(bot.intakeAuto())
                //intake third sample
                .afterTime(0.01, bot.actionHighBucketAuto())
                .strafeToLinearHeading(new Vector2d(57, 66), Math.toRadians(-145))
                .stopAndAdd(bot.actionBucketDropAuto())
                .waitSeconds(0.2)
                //outtake third sample
                .strafeToLinearHeading(new Vector2d(58.5,41.5),Math.toRadians(-50)) // stop and add close claw and transfer etc
                .stopAndAdd( bot.autoLastSample())
                //intake fourth sample
                .afterTime(0.01, bot.actionHighBucketAuto())
                .strafeToLinearHeading(new Vector2d(56.5, 62.25), Math.toRadians(-148))
                .stopAndAdd(bot.actionBucketDropAuto())
                .waitSeconds(0.15)
                //outtake fourth sample
                .splineToLinearHeading(new Pose2d(33,17,Math.toRadians(180)),Math.toRadians(-180))
                .splineToLinearHeading(new Pose2d(11,17,Math.toRadians(180)),Math.toRadians(-180))
                .stopAndAdd(bot.actionPark())
                .waitSeconds(0.5)
                .build();
                //park

        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));
    }

}
