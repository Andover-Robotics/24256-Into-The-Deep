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

        Pose2d initialPose = new Pose2d(-5, 64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(-5, 64, Math.toRadians(-90)))
                .stopAndAdd(bot.actionHighChamber())
                .strafeToLinearHeading(new Vector2d(-2, 30), Math.toRadians(-90))
                .waitSeconds(1)
                .stopAndAdd(bot.actionRelease())
                //clip preload

                .strafeToConstantHeading(new Vector2d(-35, 33))
                .strafeToConstantHeading(new Vector2d(-35, 15))
                .strafeToConstantHeading(new Vector2d(-45, 15))
                .strafeToConstantHeading(new Vector2d(-45, 60))
                .strafeToConstantHeading(new Vector2d(-45, 15))
                .strafeToConstantHeading(new Vector2d(-52, 15))
                .strafeToConstantHeading(new Vector2d(-52, 60))

                //push specimen into obs zone

                .afterTime(0.5,bot.autoSpecimen())
                .strafeToConstantHeading(new Vector2d(-45,62))
                .waitSeconds(0.5)
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-4,30))
                .waitSeconds(1)
                .stopAndAdd(bot.actionRelease())
                //second specimen

                .afterTime(0.5,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-45, 62), Math.toRadians(-90))
                .waitSeconds(0.5)
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-6, 30))
                .waitSeconds(1)
                .stopAndAdd(bot.actionRelease())
                //third specimen

                .afterTime(0.5,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-45, 62), Math.toRadians(-90))
                .waitSeconds(0.5)
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-8, 30))
                .waitSeconds(1)
                .stopAndAdd(bot.actionRelease())
                //fourth specimen

                .strafeToLinearHeading(new Vector2d(-45, 62), Math.toRadians(-90))
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





