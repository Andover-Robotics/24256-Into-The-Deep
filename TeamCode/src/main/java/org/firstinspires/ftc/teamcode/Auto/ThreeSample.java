package org.firstinspires.ftc.teamcode.Auto;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.tuning.ActionHelper;
import org.firstinspires.ftc.teamcode.Auto.tuning.MecanumDrive;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

@Autonomous(name = "DON'T USE Three Sample Auto ")
public class ThreeSample extends LinearOpMode {
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
                .waitSeconds(0.2)
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))

                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(45))
                .stopAndAdd(bot.actionRelease())
                .waitSeconds(.4)

                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .stopAndAdd(bot.actionSlidesLower())

                .strafeToLinearHeading(new Vector2d(47.5,43),Math.toRadians(-90)) // stop and add close claw and transfer etc
                .waitSeconds(.2)
                .stopAndAdd(bot.intakeAuto())
                .waitSeconds(.2)
                .afterTime(0.01, bot.actionHighBucket())
                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                //SLIDES
                .strafeToLinearHeading(new Vector2d(56, 65), Math.toRadians(45))
                .stopAndAdd(bot.actionRelease())
                .waitSeconds(.4)

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
                .waitSeconds(.4)

                .strafeToLinearHeading(new Vector2d(52, 61), Math.toRadians(45))
                .stopAndAdd(bot.actionSlidesLower())
                .strafeToLinearHeading(new Vector2d(62.3, 43),((Math.toRadians(-65))))

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