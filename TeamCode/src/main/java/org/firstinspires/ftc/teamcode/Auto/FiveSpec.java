package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;


@Autonomous(name = "fivespec", group = "Auto")
public class FiveSpec extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();

        Pose2d initialPose = new Pose2d(-10, 64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
//heading, tangent
        Action runAuto = drive.actionBuilder(new Pose2d(-10, 64, Math.toRadians(-90)))
                .stopAndAdd(bot.actionHighChamberAuto())
                .afterTime(1.5, bot.actionAutoClip())
                .strafeToLinearHeading(new Vector2d(6.6, 29), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,90))
                .waitSeconds(.5)
                //clip preload
                .afterTime(0.55,bot.actionSweep())
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(-40, 39,Math.toRadians(-120)),Math.toRadians(105), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .splineToLinearHeading(new Pose2d(-42,64,Math.toRadians(140)),Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .stopAndAdd(bot.armUp())
                .setReversed(true)
                .afterTime(0.9,bot.actionSweep())
                .splineToLinearHeading(new Pose2d(-52,32,Math.toRadians(-145)), Math.toRadians(105), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .setReversed(true)
                .afterTime(0.1, bot.autoSpecimen())
                .splineToLinearHeading(new Pose2d(-57,62,Math.toRadians(120)), Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .afterTime(0.1, bot.armUp())
                .setReversed(true)
                .afterTime(0.4,bot.actionSweep())
                .splineToLinearHeading(new Pose2d(-56.5,30,Math.toRadians(-160)), Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .splineToLinearHeading(new Pose2d(-56.5,64,Math.toRadians(160)), Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-38,67,Math.toRadians(-80)), Math.toRadians(200), drive.defaultVelConstraint, new ProfileAccelConstraint(-52,92))

                //pushAuto specimen into obs zone

                .stopAndAdd(bot.autoIntakeSpecimen())
                .afterTime(1.8, bot.actionAutoClip())
                .stopAndAdd(bot.actionHighChamberAuto())
                .setReversed(false)
                .splineToLinearHeading(new Pose2d(-2,30.5, Math.toRadians(-90)), Math.toRadians(180), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,90))
                .waitSeconds(0.3)
                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-38, 67), Math.toRadians(80), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,95))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .afterTime(1.65, bot.actionAutoClip())
                .stopAndAdd(bot.actionHighChamberAuto())
                .setReversed(false)
                .strafeToLinearHeading(new Vector2d(-2, 45), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .splineToConstantHeading(new Vector2d(-3, 30.5),Math.toRadians(130), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,95))
                .waitSeconds(0.4)
                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-38, 67), Math.toRadians(80), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .afterTime(2.1, bot.actionAutoClip())
                .stopAndAdd(bot.actionHighChamberAuto())
                .waitSeconds(0.2)
                .strafeToLinearHeading(new Vector2d(-3, 45), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .splineToLinearHeading(new Pose2d(-4.5, 30, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .waitSeconds(0.4)

                .afterTime(0.4,bot.autoSpecimen())
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-38, 67), Math.toRadians(80), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .afterTime(2, bot.actionAutoClip())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToLinearHeading(new Vector2d(-6, 45), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .splineToLinearHeading(new Pose2d(-7, 30.5, Math.toRadians(-90)), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-55,92))
                .afterTime(1,bot.toIntake())
                .waitSeconds(0.5)
                .setReversed(true)
                .splineTo(new Vector2d(-60, 60), Math.toRadians(-15), drive.defaultVelConstraint, new ProfileAccelConstraint(-300,800))
                //fifth

                        .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





