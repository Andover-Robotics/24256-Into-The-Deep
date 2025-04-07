package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

@Autonomous(name = "SixSample", group = "Auto")
public class SixSample extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException{
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();
        Pose2d initialPose = new Pose2d(38,64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(38,63, Math.toRadians(-150)))
                .afterTime(0.001, bot.savingBottomWrist())
                .afterTime(0.2,   bot.actionHighBucketAuto())
                .setReversed(true)
                .afterTime(1.5, bot.actionBucketDropAuto())
                .splineToLinearHeading(new Pose2d(58.5,62,Math.toRadians(-135)),Math.toRadians(90))
                .waitSeconds(1.2)
                //Outtake preload
                .afterTime(0.000000000000001, bot.toIntake())
                .afterTime(1.5, bot.intakeAuto())
                .afterTime(3.8, bot.actionHighBucketAuto())
                .strafeToLinearHeading(new Vector2d(43.4,44),Math.toRadians(-80))
                .waitSeconds(1)
                //intake second sample
                .afterTime(0.4, bot.actionBucketDropAuto())
                .strafeToLinearHeading(new Vector2d(57.75, 62.75), Math.toRadians(-135))
                .waitSeconds(0.3)

                //outtake second sample
                .afterTime(0.8, bot.intakeAuto())
                .afterTime(3.4, bot.actionHighBucketAuto())
                .strafeToLinearHeading(new Vector2d(57,43.5),Math.toRadians(-80))
                .waitSeconds(2.3)
                //intake third sample
                .afterTime(0.3, bot.actionBucketDropAuto())
                .strafeToLinearHeading(new Vector2d(57, 66), Math.toRadians(-145))
                .waitSeconds(0.5)
                //outtake third sample
                .afterTime(0.8, bot.autoSpecialSample())
                .afterTime(3.5, bot.actionHighBucketAuto())
                .strafeToLinearHeading(new Vector2d(58.5,41.5),Math.toRadians(-50))
                .waitSeconds(3.4)
                //intake fourth sample
                .afterTime(0.3, bot.actionBucketDropAuto())
                .strafeToLinearHeading(new Vector2d(56.5, 62.25), Math.toRadians(-148))
                .waitSeconds(0.3)
                //outtake fourth sample
                .splineToLinearHeading(new Pose2d(11,-10,Math.toRadians(180)),Math.toRadians(-180))
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
