package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

@Autonomous(name = "Specimen Auto ")
public class SpecimenAuto extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();

        Pose2d initialPose = new Pose2d(-20, 64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(-20, 64, Math.toRadians(-90)))
                .stopAndAdd(bot.actionHighChamber())
                .strafeToLinearHeading(new Vector2d(-20, 35), Math.toRadians(-90))
                .waitSeconds(1)
                .stopAndAdd(bot.actionRelease())
                //clip preload

                .strafeToConstantHeading(new Vector2d(-35, 35))
                .strafeToConstantHeading(new Vector2d(-35, 0))
                .strafeToConstantHeading(new Vector2d(-45, 0))
                .strafeToConstantHeading(new Vector2d(-45, 60))
                //push specimens into obs zone

                .afterTime(0.5,bot.autoSpecimen())
                .strafeToConstantHeading(new Vector2d(-20,64))
                .waitSeconds(0.5)
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-19,35))
                .waitSeconds(1)
                .stopAndAdd(bot.actionRelease())
                //second specimen

                .afterTime(0.5,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-35, 60), Math.toRadians(-90))
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-18, 35))
                .waitSeconds(1)
                .stopAndAdd(bot.actionRelease())
                //third specimen
                .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





