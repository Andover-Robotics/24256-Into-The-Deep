package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

@Autonomous(name = "Specimen Sweep Auto")
public class SpecimenSweepAuto extends LinearOpMode {
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
                .strafeToLinearHeading(new Vector2d(5.5, 30), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-27,57))
                .stopAndAdd(bot.actionAutoClip())

                .afterTime(0.01,bot.actionSlidesLower())
                //clip preload
                .afterTime(1.4,bot.actionSweep())
                .strafeToConstantHeading(new Vector2d(-20,43))
                .splineToLinearHeading(new Pose2d(-42, 39,Math.toRadians(-120)),Math.toRadians(55))
                .splineToLinearHeading(new Pose2d(-44,64,Math.toRadians(140)),Math.toRadians(75), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,75))
                .stopAndAdd(bot.armUp())
                .afterTime(0.9,bot.actionSweep())
                .strafeToLinearHeading(new Vector2d(-51,34),Math.toRadians(-120))
                .afterTime(0.1, bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-53,62),Math.toRadians(120), drive.defaultVelConstraint, new ProfileAccelConstraint(-50,75))
                .strafeToLinearHeading(new Vector2d(-38,57),Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-38,67.5),Math.toRadians(-90))




                //pushAuto specimen into obs zone

                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(1,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(1,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 56), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,67.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-1.5,45),drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-1.5,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-32, 56), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,67.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-4,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //fourth specimen

                .strafeToConstantHeading(new Vector2d(-15, 50))
                .afterTime(0.001,bot.toIntake())
                .splineToLinearHeading(new Pose2d(-52, 64,Math.toRadians(140)),Math.toRadians(80),drive.defaultVelConstraint, new ProfileAccelConstraint(-40,100))

                .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





