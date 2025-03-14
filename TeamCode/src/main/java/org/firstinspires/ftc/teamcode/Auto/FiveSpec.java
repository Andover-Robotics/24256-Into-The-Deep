package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;


@Autonomous(name = "Specimen Sweep Auto", group = "Auto")
public class FiveSpec extends LinearOpMode {
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
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToLinearHeading(new Vector2d(6, 29), Math.toRadians(-90))
                .stopAndAdd(bot.actionAutoClip())
                .afterTime(0.01,bot.actionSlidesLower())

                //first specimen

                .afterTime(1.4,bot.actionSweep())
                .strafeToConstantHeading(new Vector2d(-20,43))
                .splineToLinearHeading(new Pose2d(-42, 39,Math.toRadians(-120)),Math.toRadians(55))
                .strafeToConstantHeading(new Vector2d(-43,63))
                .afterTime(0.01, bot.armUp())
                .splineToLinearHeading(new Pose2d(-44,64,Math.toRadians(140)),Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,80))
                .afterTime(0.9,bot.actionSweep())
                .strafeToLinearHeading(new Vector2d(-51,34),Math.toRadians(-120))
                .splineToLinearHeading(new Pose2d(-53, 62,Math.toRadians(120)), Math.toRadians(55), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,80))
                .stopAndAdd(bot.armUp())
                .afterTime(0.9,bot.actionSweep())
                .strafeToLinearHeading(new Vector2d(-58,34),Math.toRadians(-120))
                .afterTime(0.01, bot.autoSpecimen())
                .afterTime(0.2, bot.armUp())
                .splineToLinearHeading(new Pose2d(-58,60,Math.toRadians(-90)), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-45,68),Math.toRadians(-90))

                //push specimen into observation zone

                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(1,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-40,100))
                .splineToConstantHeading(new Vector2d(1,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,85))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())

                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-45, 60), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-40,100))
                .splineToConstantHeading(new Vector2d(-45,67.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,85))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-1.5,45),drive.defaultVelConstraint, new ProfileAccelConstraint(-40,100))
                .splineToConstantHeading(new Vector2d(-1.5,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-40,85))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())

                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-45, 60), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,100))
                .splineToConstantHeading(new Vector2d(-45,67.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-40,80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,100))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-40,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())

                //fourth specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-45, 60), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,100))
                .splineToConstantHeading(new Vector2d(-45,67.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,100))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-40,85))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())

                //fifth specimen

                .strafeToConstantHeading(new Vector2d(-15, 50), drive.defaultVelConstraint, new ProfileAccelConstraint(-100,200))
                .afterTime(0.001,bot.toIntake())
                .splineToLinearHeading(new Pose2d(-45,63,Math.toRadians(90)),Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-100,200))


                        .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





