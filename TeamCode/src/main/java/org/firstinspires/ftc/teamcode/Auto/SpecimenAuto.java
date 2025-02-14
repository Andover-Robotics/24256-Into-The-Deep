package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

@Autonomous(name = "Specimen Auto")
public class SpecimenAuto extends LinearOpMode {
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
                .stopAndAdd(bot.actionHighChamber())
                .strafeToLinearHeading(new Vector2d(3, 30.5), Math.toRadians(-90))
                .waitSeconds(0.3)
                .stopAndAdd(bot.actionAutoClip())
                .waitSeconds(0.5)
                .stopAndAdd(bot.actionSlidesLower())
                //clip preload
                .strafeToConstantHeading(new Vector2d(0, 40))
                .strafeToConstantHeading(new Vector2d(-20,42))
                .strafeToConstantHeading(new Vector2d(-20, 46))
                .splineToConstantHeading(new Vector2d(-35,15),90)
                /*.strafeToConstantHeading(new Vector2d(-35, 33))
                .strafeToConstantHeading(new Vector2d(-35, 15))

                //pushes second sample to obs zone
                .splineToConstantHeading(new Vector2d(-35,15),90)
                .splineToConstantHeading(new Vector2d(-41,15),90)*/

                .afterTime(0.00001,bot.autoSpecimen())
                .splineToConstantHeading(new Vector2d(-45, 60),90)
                .strafeToConstantHeading(new Vector2d(-36.5, 56))
                //push specimen into obs zone

                .strafeToConstantHeading(new Vector2d(-36.59,63.2))
                .waitSeconds(0.3)
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(0,37))
                .strafeToConstantHeading(new Vector2d(0,31))
                .waitSeconds(0.3)
                .stopAndAdd(bot.actionAutoClip())
                .waitSeconds(0.5)
                .stopAndAdd(bot.actionSlidesLower())
                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(0, 34), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-38, 58), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-38, 63.2), Math.toRadians(-90))
                .waitSeconds(0.4)
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-2,40))
                .strafeToConstantHeading(new Vector2d(-2,31))
                .waitSeconds(0.4)
                .stopAndAdd(bot.actionAutoClip())
                .waitSeconds(0.6)
                .stopAndAdd(bot.actionSlidesLower())
                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-2, 34), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-38, 58), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-38, 63.2), Math.toRadians(-90))
                .waitSeconds(0.4)
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-4,40))
                .strafeToConstantHeading(new Vector2d(-4,31))
                .waitSeconds(0.4)
                .stopAndAdd(bot.actionAutoClip())
                .waitSeconds(0.6)
                .stopAndAdd(bot.actionSlidesLower())
                //fourth specimen

                .splineToConstantHeading(new Vector2d(-45, 60),90)
                .afterTime(0.001,bot.toIntake())
                //park

                .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





