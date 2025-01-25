package org.firstinspires.ftc.teamcode.Auto;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import org.firstinspires.ftc.teamcode.Auto.ActionHelper;
import org.firstinspires.ftc.teamcode.Auto.ActionHelper;
@Autonomous(name = "Bucket Auto rr")
public class BucketAuto extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException{
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();

        Pose2d initialPose = new Pose2d(38,64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(38,63, Math.toRadians(-90)))
                .stopAndAdd(bot.armFlip())
                .waitSeconds(.5)
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))

                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(45))
                .stopAndAdd(bot.actionRelease())

                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .stopAndAdd(bot.actionSlidesLower())

                .strafeToLinearHeading(new Vector2d(48.5,43),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(.2)
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                //SLIDES
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(45))
                .stopAndAdd(bot.actionRelease())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .stopAndAdd(bot.actionSlidesLower())

                .strafeToLinearHeading(new Vector2d(57.5,43),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())

                .waitSeconds(0.2)
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                //SLIDES
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(45))
                .stopAndAdd(bot.actionRelease())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .stopAndAdd(bot.actionSlidesLower())
                .strafeToLinearHeading(new Vector2d(61.3, 42.5),((Math.toRadians(-63))))
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(0.2)
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                //SLIDES
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(45))
                .stopAndAdd(bot.actionRelease())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(1)
                .build();

        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));
    }

}