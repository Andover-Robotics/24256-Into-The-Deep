package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;


@Autonomous(name = "Tele", group = "Auto")
public class TeleAuto extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();

        Pose2d initialPose = new Pose2d(-60, 64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(-60, 64, Math.toRadians(-90)))
                .afterTime(0.0001, bot.autoSpecimen())
                .strafeToConstantHeading(new Vector2d(-38,60))
                .strafeToConstantHeading(new Vector2d(-40,67.5))
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(1,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(1,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.2)

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 57), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,58),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .strafeToConstantHeading(new Vector2d(-38,63))
                .strafeToConstantHeading(new Vector2d(-38,67.5))
                .waitSeconds(0.3)
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-1.5,45),drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-1.5,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 57), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,58),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .strafeToConstantHeading(new Vector2d(-38,63))
                .strafeToConstantHeading(new Vector2d(-38,67.5))
                .waitSeconds(0.3)
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 57), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,58),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .strafeToConstantHeading(new Vector2d(-38,63))
                .strafeToConstantHeading(new Vector2d(-38,67.5))
                .waitSeconds(0.3)
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 57), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,58),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .strafeToConstantHeading(new Vector2d(-38,63))
                .strafeToConstantHeading(new Vector2d(-38,67.5))
                .waitSeconds(0.3)
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 57), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,58),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .strafeToConstantHeading(new Vector2d(-38,63))
                .strafeToConstantHeading(new Vector2d(-38,67.5))
                .waitSeconds(0.3)
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 57), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,58),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .strafeToConstantHeading(new Vector2d(-38,63))
                .strafeToConstantHeading(new Vector2d(-38,67.5))
                .waitSeconds(0.3)
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 57), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,58),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .strafeToConstantHeading(new Vector2d(-38,63))
                .strafeToConstantHeading(new Vector2d(-38,67.5))
                .waitSeconds(0.3)
                .stopAndAdd(bot.autoIntakeSpecimen())

                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                .waitSeconds(0.25)

                .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





